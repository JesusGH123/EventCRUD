<%--
  Created by IntelliJ IDEA.
  User: Jesus
  Date: 05/06/2021
  Time: 11:53 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>

    <!--Made with love by Mutiullah Samim -->
    <!--Bootsrap 4 CDN-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <!--Fontawesome CDN-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <!--Custom style-->
    <link rel="stylesheet" type="text/css" href="homeStyle.css">
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" role="navigation">
        <div class="container">
            <a class="navbar-brand" href="#">Eventos Morelos</a>
            <button class="navbar-toggler border-0" type="button" data-toggle="collapse" data-target="#exCollapsingNavbar">
                &#9776;
            </button>
            <!--
            <div class="collapse navbar-collapse" id="exCollapsingNavbar">
                <ul class="nav navbar-nav">
                    <li class="nav-item"><a href="#" class="nav-link">About</a></li>
                    <li class="nav-item"><a href="#" class="nav-link">Link</a></li>
                    <li class="nav-item"><a href="#" class="nav-link">Service</a></li>
                    <li class="nav-item"><a href="#" class="nav-link">More</a></li>
                </ul>
            -->
                <ul class="nav navbar-nav flex-row justify-content-between ml-auto">
                    <!-- <li class="nav-item order-2 order-md-1"><a href="#" class="nav-link" title="settings"><i class="fa fa-cog fa-fw fa-lg"></i></a></li> -->
                    <li class="dropdown order-1">
                        <button type="button" id="dropdownMenu1" data-toggle="dropdown" class="btn btn-outline-secondary dropdown-toggle"><li class="nav-item order-2 order-md-1"><a href="#" class="nav-link" title="settings"><i class="fa fa-cog fa-fw fa-lg"></i></a></li></button>
                        <ul class="dropdown-menu dropdown-menu-right mt-2">
                            <li class="px-3 py-2">
                                <div>Username: Mike</div>
                                <i class="fas fa-sign-out-alt"></i>
                                <!--<form class="form" role="form">
                                    <div class="form-group">
                                        <input id="emailInput" placeholder="Email" class="form-control form-control-sm" type="text" required="">
                                    </div>
                                    <div class="form-group">
                                        <input id="passwordInput" placeholder="Password" class="form-control form-control-sm" type="text" required="">
                                    </div>
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-primary btn-block">Login</button>
                                    </div>
                                    <div class="form-group text-center">
                                        <small><a href="#" data-toggle="modal" data-target="#modalPassword">Forgot password?</a></small>
                                    </div>
                                </form>-->
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
                    <button type="button" class="close font-weight-light" data-dismiss="modal" aria-hidden="true">×</button>
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

    <!-- List -->
    <div class="container-fluid px-1 px-sm-4 py-5 mx-auto">
        <div class="row d-flex justify-content-start card-strip">
            <img class="comp-logo mr-4 mb-3" src="https://becasparatodos.com/wp-content/uploads/2016/12/tec-de-monterrey-becas-para-mexicanos.jpg">
            <div class="info">

                <div class="row px-3 mb-2">
                    <span class="sold_out">Agotado</span>
                    <h4 class="dark-text mr-4">Carrera Tec</h4>
                    <p class="mt-1 mr-4 extended-title">Deportivo</p>
                    <span class="dot"></span>
                </div>
                <div class="row px-3">
                    <p class="mb-1"><span class="fa fa-clock-o"> 20-08-2021 03:00 p.m. - 05:00 p.m.</span></p>
                </div>
                <div class="row px-3">
                    <p><span class="fa fa-clock-o">Autopista del Sol KM 104 Col. Real del Puente, Xochitepec Morelos</span></p>
                </div>
                <div class="row px-3 mb-3"> <span class="fa fa-star pr-1 mt-1 active"></span> <span class="fa fa-star pr-1 mt-1 active"></span> <span class="fa fa-star pr-1 mt-1 active"></span> <span class="fa fa-star pr-1 mt-1"></span> <span class="fa fa-star pr-1 mt-1 mr-2"></span> <span><strong>28</strong> asistencias</span> </div>
                <div class="row px-3">
                    <h6 class="green-block">Mas información</h6>
                </div>
            </div>
            <div class="v-line ml-auto"></div>
            <div class="price">
                <p class="mb-0">Precio</p>
                <div class="row px-3">
                    <h4 class="blue-text mr-2">$150.00</h4>
                </div>
                <div class="btn btn-orange mt-4">Asistir</div>
                <i class="btn btn-orange mt-4 fas fa-trash-alt"></i>
                <i class="btn btn-orange mt-4 fas fa-edit"></i>
            </div>

        </div>
        <div class="row d-flex justify-content-start card-strip">
            <img class="comp-logo mr-4 mb-3" src="https://becasparatodos.com/wp-content/uploads/2016/12/tec-de-monterrey-becas-para-mexicanos.jpg">
            <div class="info">

                <div class="row px-3 mb-2">
                    <span class="sold_out">Agotado</span>
                    <h4 class="dark-text mr-4">Carrera Tec</h4>
                    <p class="mt-1 mr-4 extended-title">Deportivo</p>
                    <span class="dot"></span>
                </div>
                <div class="row px-3">
                    <p class="mb-1"><span class="fa fa-clock-o"> 20-08-2021 03:00 p.m. - 05:00 p.m.</span></p>
                </div>
                <div class="row px-3">
                    <p><span class="fa fa-clock-o">Autopista del Sol KM 104 Col. Real del Puente, Xochitepec Morelos</span></p>
                </div>
                <div class="row px-3 mb-3"> <span class="fa fa-star pr-1 mt-1 active"></span> <span class="fa fa-star pr-1 mt-1 active"></span> <span class="fa fa-star pr-1 mt-1 active"></span> <span class="fa fa-star pr-1 mt-1"></span> <span class="fa fa-star pr-1 mt-1 mr-2"></span> <span><strong>28</strong> asistencias</span> </div>
                <div class="row px-3">
                    <h6 class="green-block">Mas información</h6>
                </div>
            </div>
            <div class="v-line ml-auto"></div>
            <div class="price">
                <p class="mb-0">Precio</p>
                <div class="row px-3">
                    <h4 class="blue-text mr-2">$150.00</h4>
                </div>
                <div class="btn btn-orange mt-4">Asistir</div>
                <i class="btn btn-orange mt-4 fas fa-trash-alt"></i>
                <i class="btn btn-orange mt-4 fas fa-edit"></i>
            </div>

        </div>
        <!--
        <div class="row d-flex justify-content-start card-strip"> <img class="comp-logo mr-4 mb-3" src="https://i.imgur.com/8ScTzSD.jpg">
            <div class="info">
                <div class="row px-3 mb-2">
                    <h4 class="dark-text mr-4">Analise de Sistemas</h4>
                    <p class="mt-1 mr-4 extended-title">Bacharelado</p>
                </div>
                <div class="row px-3">
                    <p class="mb-1"><span class="fa fa-clock-o"> Noite</span></p>
                    <p class="mb-1"><span class="fa fa-home ml-4"> Presencial</span></p>
                </div>
                <div class="row px-3">
                    <p><span class="fa fa-clock-o"> Jardim Esplanada - S&o Jose dos Campos</span></p>
                </div>
                <div class="row px-3 mb-3"> <span class="fa fa-star pr-1 mt-1 active"></span> <span class="fa fa-star pr-1 mt-1 active"></span> <span class="fa fa-star pr-1 mt-1 active"></span> <span class="fa fa-star pr-1 mt-1"></span> <span class="fa fa-star pr-1 mt-1 mr-2"></span> <span><strong>28</strong> avaliagoes</span> </div>
                <div class="row px-3">
                    <h6 class="green-block">Mensalidade de julho gratis</h6>
                </div>
            </div>
            <div class="v-line ml-auto"></div>
            <div class="price">
                <p class="mb-0">Bolsa de</p>
                <h4 class="blue-text mb-3">50%</h4>
                <p class="mb-0">Mensalidade</p>
                <div class="row px-3">
                    <h4 class="blue-text mr-2">R$ 410,00</h4>
                    <p class="mt-1 price-fall mr-5"><del>R$ 820,00</del></p>
                </div>
                <div class="btn btn-orange mt-4">Detalhes desta bolsa</div>
            </div>
        </div>
        <div class="row d-flex justify-content-start card-strip"> <img class="comp-logo mr-4 mb-3" src="https://i.imgur.com/BKoqCse.jpg">
            <div class="info">
                <div class="row px-3 mb-2">
                    <h4 class="dark-text mr-4">Analise de Sistemas</h4>
                    <p class="mt-1 mr-4 extended-title">Bacharelado</p>
                </div>
                <div class="row px-3">
                    <p class="mb-1"><span class="fa fa-clock-o"> Noite</span></p>
                    <p class="mb-1"><span class="fa fa-home ml-4"> Presencial</span></p>
                </div>
                <div class="row px-3">
                    <p><span class="fa fa-clock-o"> Jardim Esplanada - S&o Jose dos Campos</span></p>
                </div>
                <div class="row px-3 mb-3"> <span class="fa fa-star pr-1 mt-1 active"></span> <span class="fa fa-star pr-1 mt-1 active"></span> <span class="fa fa-star pr-1 mt-1 active"></span> <span class="fa fa-star pr-1 mt-1"></span> <span class="fa fa-star pr-1 mt-1 mr-2"></span> <span><strong>28</strong> avaliagoes</span> </div>
                <div class="row px-3">
                    <h6 class="green-block">Mensalidade de julho gratis</h6>
                </div>
            </div>
            <div class="v-line ml-auto"></div>
            <div class="price">
                <p class="mb-0">Bolsa de</p>
                <h4 class="blue-text mb-3">50%</h4>
                <p class="mb-0">Mensalidade</p>
                <div class="row px-3">
                    <h4 class="blue-text mr-2">R$ 320,00</h4>
                    <p class="mt-1 price-fall mr-5"><del>R$ 640,00</del></p>
                </div>
                <div class="btn btn-orange mt-4">Detalhes desta bolsa</div>
            </div>
        </div>
        -->
    </div>

    <!-- Floating button -->
    <a href="#" class="float">
        <i class="fa fa-plus my-float"></i>
    </a>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</body>
</html>
