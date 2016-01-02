<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<title>Login page</title>
<style type="text/css">
.wrapper{width:500px;margin-left:auto;margin-right:auto}
label{padding-left:0 !important}
</style>
</head>
<body>
<c:if test="${param.error != null}">
 <div class="alert alert-danger">${SPRING_SECURITY_EXCEPTION}</div>
</c:if>
    <div class="container">
        <div class="row wrapper">
        <h1>Login</h1>
            <form name='f' action="j_spring_security_check" method='POST'>
                <label class="col-sm-4">Email:</label>
                <span class="col-sm-8"><input class="form-control" type='text' name='j_username' value=''></span>
                <br><br>        
                <label class="col-sm-4">Password:</label>
                <span class="col-sm-8"><input class="form-control" type='password' name='j_password' /></span>
                <br><br>
                <input class="btn btn-primary" name="submit" type="submit" value="Submit"/>
            </form>
            <a class="btn btn-default" href="<c:url value="registration.html" />">Registration</a>
        </div>
    </div>
</body>

</html>