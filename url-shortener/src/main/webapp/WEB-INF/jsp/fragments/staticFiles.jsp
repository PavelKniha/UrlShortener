<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>PetClinic :: a Spring Framework demonstration</title>
    <spring:url value="/webjars/bootstrap/2.3.0/css/bootstrap.min.css" var="bootstrapCss"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <spring:url value="/webjars/jquery/2.0.3/jquery.js" var="jQuery"/>
    <script src="${jQuery}"></script>
    <spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.core.js" var="jQueryUiCore"/>
    <script src="${jQueryUiCore}"></script>
    <spring:url value="/webjars/jquery-ui/1.10.3/themes/base/jquery-ui.css" var="jQueryUiCss"/>
    <link href="${jQueryUiCss}" rel="stylesheet"></link>
</head>


