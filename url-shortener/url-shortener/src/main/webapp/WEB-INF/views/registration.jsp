<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>registration form</title>
</head>
<body>
    <div class="container">
        <div>
            <h1>Registration</h1>
            <br>
            <form action="/" method="POST" enctype="utf8">
                <div class="form-group row" >
                    <label class="col-sm-3">FirstName</label>
                    <span class="col-sm-5"><input class="form-control" name="firstName" value="" required/></span>
                    <span id="firstNameError" class="alert alert-danger col-sm-4" style="display:none"></span>
                </div>
                <div class="form-group row">
                    <label class="col-sm-3">LastName</label>
                    <span class="col-sm-5"><input class="form-control" name="lastName" value="" required/></span>
                    <span id="lastNameError" class="alert alert-danger col-sm-4" style="display:none"></span>   
                </div>
                <div class="form-group row">
                    <label class="col-sm-3">Email</label>
                    <span class="col-sm-5"><input type="email" class="form-control" name="email" value="" required/></span>                    
                    <span id="emailError" class="alert alert-danger col-sm-4" style="display:none"></span>
                    
                </div>
                <div class="form-group row">
                    <label class="col-sm-3">Password</label>
                    <span class="col-sm-5"><input id="password" class="form-control" name="password" value="" type="password" required/></span>
                    <span id="passwordError" class="alert alert-danger col-sm-4" style="display:none"></span>
                	<span id="globalError" class="alert alert-danger col-sm-4" style="display:none"></span>
                </div>
                <br>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
            <br> 
            <a href="<c:url value="login.html" />">Login</a>
        </div>
    </div>

<script type="text/javascript">
$(document).ready(function () {
	$('form').submit(function(event) {
		register(event);
	});

});

function register(event){
	event.preventDefault();
    var formData= $('form').serialize();
    $.post("<c:url value="/user/registration"/>",formData ,function(data){
        if(data.message == "success"){
            window.location.href = "<c:url value="/registerSuccess.html"></c:url>";
        }
        
    })
    .fail(function(data) {
		if(data.responseJSON.error == "UserAlreadyExist"){
            $("#emailError").show().html(data.responseJSON.message);
        }
        else if(data.responseJSON.error.indexOf("InternalError") > -1){
            window.location.href = "<c:url value="/login.html"></c:url>" + "?message=" + data.responseJSON.message;
        }
        else{
        	var errors = $.parseJSON(data.responseJSON.message);
            $.each( errors, function( index,item ){
                $("#"+item.field+"Error").show().html(item.defaultMessage);
            });
            errors = $.parseJSON(data.responseJSON.error);
            $.each( errors, function( index,item ){
                $("#globalError").show().append(item.defaultMessage+"<br>");
            });
        }
    });
}
</script>
</body>

</html>