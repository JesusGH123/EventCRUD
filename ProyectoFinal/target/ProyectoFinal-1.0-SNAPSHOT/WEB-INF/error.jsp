<%--
  Created by IntelliJ IDEA.
  User: MIG1-
  Date: 6/12/2021
  Time: 4:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/error.css">
    <title>Error</title>

</head>
<body>
<section class="notFound">
    <div class="img">
        <img src="https://assets.codepen.io/5647096/backToTheHomepage.png" alt="Back to the Homepage"/>
        <img src="https://assets.codepen.io/5647096/Delorean.png" alt="El Delorean, El Doc y Marti McFly"/>
    </div>
    <div class="text">
        <h1>404</h1>
        <h2>PAGE NOT FOUND</h2>
        <a href="javascript:history.back()" class="yes">REGRESAR</a>
    </div>
</section>
</body>
</html>
