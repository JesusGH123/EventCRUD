
<!------ Include the above in your HEAD tag ---------->

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <!--Made with love by Mutiullah Samim -->
    <!--Bootsrap 4 CDN-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <!--Fontawesome CDN-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <!--Custom styles-->
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/loginStyle.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/users.css">
</head>
<body>
<h1 style="display: inline; margin-left:3rem">Eventos Morelos</h1>
<img src="images/morelos.png" style="display: inline" height="50" width="50">
<div class="container">
    <div class="d-flex justify-content-center h-100">
        <div class="card">
            <div class="card-header">
                <h3>
                    Iniciar sesión
                </h3>
                <!-- <div class="d-flex justify-content-end social_icon">
                    <span><i class="fab fa-facebook-square"></i></span>
                    <span><i class="fab fa-google-plus-square"></i></span>
                    <span><i class="fab fa-twitter-square"></i></span>
                </div> -->
            </div>
            <div class="card-body">
                <form method="POST" action="login" id="loginForm">
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-user"></i></span>
                        </div>
                        <input type="text" name="username" class="form-control" placeholder="Usuario">

                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-key"></i></span>
                        </div>
                        <input type="password" name="password" class="form-control" placeholder="Contraseña">
                    </div>
                    <!-- <div class="row align-items-center remember">
                        <input type="checkbox">Remember Me
                    </div> -->

                    <div class="form-group">


                        <input type="submit" value="Iniciar sesión" class="btn login_btn">
                        <input type="button" id="addBtn" value="¿No tienes cuenta?" class="btn float-right login_btn">
                    </div>



                </form>
            </div>
            <div class="card-footer">
                <div style="color: white">
                    <b>${message}</b>
                </div>
            </div>
        </div>

    </div>

</div>
<script src="${pageContext.servletContext.contextPath}/login.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</body>
</html>