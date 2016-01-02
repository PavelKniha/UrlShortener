<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<jsp:include page="fragments/staticFiles.jsp"/>
<body>
<datatables:table id="users" data="${selections}" cdn="true" row="user" theme="bootstrap2" cssClass="table table-striped">
    <datatables:column title="Name" cssStyle="width: 150px;" >
        <spring:url value="users/{userId}.html" var="userUrl" >
            <spring:param name="userId" value="${user.id}"/>
        </spring:url>
        <a href="${fn:escapeXml(userUrl)}"><c:out value="${user.firstName} ${user.lastName}"/></a>
    </datatables:column>
    <datatables:column title="Roles" cssStyle="width: 100px;">
        <c:forEach var="rol" items="${user.roles}">
            <c:out value="${rol.name}"/>
        </c:forEach>
    </datatables:column>
</datatables:table>
</body>
</html>