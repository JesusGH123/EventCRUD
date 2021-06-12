
<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

    <title>Login</title>
    <!--Made with love by Mutiullah Samim -->
    <!--Bootsrap 4 CDN-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <!--Fontawesome CDN-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <!--Custom styles-->
    <link rel="stylesheet" type="text/css" href="loginStyle.css">

</head>
<body>
<div class="container">
    <div class="d-flex justify-content-center h-100">
        <div class="card">
            <div class="card-header">
                <h3>
                    <c:choose>
                        <c:when test="${param.auth_type==1}">
                            Iniciar sesión
                        </c:when>
                        <c:when test="${param.auth_type==2}">
                            Registrar
                        </c:when>
                    </c:choose>
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
                        <!-- <input type="submit" value="${param.auth_type==1?"Iniciar sesión":"Registrar"}" class="btn float-right login_btn"> -->
                        <c:choose>
                            <c:when test="${param.auth_type==1}">
                                <input type="submit" value="Iniciar sesión" class="btn float-right login_btn">
                            </c:when>
                            <c:when test="${param.auth_type==2}">
                                <input type="submit" value="Registrar" class="btn float-right login_btn" onclick="addUserFromRegister()">
                            </c:when>
                        </c:choose>
                    </div>
                </form>
            </div>
            <div class="card-footer">
                <div class="d-flex justify-content-center links">
                    <c:choose>
                        <c:when test="${param.auth_type==1}" >
                            ¿No tienes cuenta?
                            <a href="?auth_type=2">Registrarme</a>
                        </c:when>
                        <c:when test="${param.auth_type==2}">
                            ¿Ya tienes cuenta?
                            <a href="?auth_type=1">Iniciar sesión</a>
                        </c:when>
                    </c:choose>
                </div>
                <div style="color: white">
                    <b>${message}</b>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="users.js"></script
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>