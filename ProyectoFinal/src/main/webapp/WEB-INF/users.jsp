<%--
  Created by IntelliJ IDEA.
  User: Jesus
  Date: 05/06/2021
  Time: 11:53 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
    <!--Made with love by Mutiullah Samim -->
    <!--Bootsrap 4 CDN-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <!--Fontawesome CDN-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <!-- Custom style -->
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/users.css">
</head>
<body>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" role="navigation">
    <div class="container">
        <a class="navbar-brand" href="#">Eventos Morelos</a>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="event">Home<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="user">Usuarios</a>
                </li>
            </ul>
        </div>
        <button class="navbar-toggler border-0" type="button" data-toggle="collapse" data-target="#exCollapsingNavbar">
            &#9776;
        </button>
        <ul class="nav navbar-nav flex-row justify-content-between ml-auto">
            <!-- <li class="nav-item order-2 order-md-1"><a href="#" class="nav-link" title="settings"><i class="fa fa-cog fa-fw fa-lg"></i></a></li> -->
            <li class="nav-item">
            </li>
            <li class="dropdown order-1">
                <button type="button" id="dropdownMenu1" data-toggle="dropdown" class="btn btn-outline-secondary dropdown-toggle"><li class="nav-item order-2 order-md-1"><a href="#" class="nav-link" title="settings"><i class="fa fa-cog fa-fw fa-lg"></i></a></li></button>
            <ul class="dropdown-menu dropdown-menu-right mt-2">
                <li class="px-3 py-2">
                    <div><c:out value="Username: ${username}"/></div>
                    <c:out value="${isAdmin?'??Eres Admin!':''}"/>
                    <button onclick="logout()"><i class="fas fa-sign-out-alt"><a>Cerrar Sesi??n</a></i></button>
                </li>
            </ul>
            </li>
        </ul>
    </div>
    </div>
</nav>

<div id="modalPassword" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3>Forgot password</h3>
                <button type="button" class="close font-weight-light" data-dismiss="modal" aria-hidden="true">??</button>
            </div>
            <div class="modal-body">
                <p>Reset your password..</p>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                <button class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>

<!-- Users list -->
<div class="container">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Usuario</th>
            <th scope="col">Rol Especial</th>
            <th scope="col">Opciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user" varStatus="i">
            <tr>
                <td scope="row">${i.index + 1}</td>
                <td><i class="fas fa-user"></i><c:out value="${user.username}" escapeXml="true"/></td>
                <td scope="row"><c:out value="${user.admin? 'Admin' : ''}"/></td>
                <td>
                    <i class="btn btn-orange mt-4 fas fa-trash-alt" data-id="${user.user_id}"></i>
                    <i class="btn btn-orange mt-4 fas fa-edit" data-id=${user.user_id}></i>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<!-- Floating button -->
<a id="addBtn" href="#" class="float">
    <i class="fa fa-plus my-float"></i>
</a>

<script src="${pageContext.servletContext.contextPath}/users.js" type="module"></script>
<script src="${pageContext.servletContext.contextPath}/auth_common.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</body>
</html>

