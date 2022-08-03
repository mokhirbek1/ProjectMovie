<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>

    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
            integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
          integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/js/all.min.js"
            integrity="sha512-6PM0qYu5KExuNcKt5bURAoT6KCThUmHRewN3zUFNaoI6Di7XJPTMoT6K0nsagZKk2OB4L7E3q1uQKHNHd4stIQ=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>

    <style>
        <%@include file="/css/style.css" %>
    </style>
    <style>
        <%@include file="/css/navbar.css" %>
    </style>
</head>
<body>
<input type="hidden" id="login_message" value="<%= request.getAttribute("login_message") %>"/>

<nav class="container-movie-navbar">
    <div class="container-navbar">
        <div class="nav-link-left">
            <form action="${pageContext.request.contextPath}/controller.do">
                <input type="hidden" name="command" value="home_page"/>
                <input type="submit" class="nav-link-home" value="Home page"/>
            </form>
        </div>
        <form class="nav-link-logout" action="${pageContext.request.contextPath}/controller.do">
            <input type="hidden" name="command" value="logout">
            <input class="nav-link-logout" type="submit" value="Logout">
        </form>
    </div>
</nav>

<span class="movie-title d-inline-block" tabindex="0" data-bs-toggle="tooltip" id="serials"
      title="Отключенная подсказка">
    <form action="${pageContext.request.contextPath}/controller.do">
        <input type="hidden" name="command" value="movie_category_page">
        <input type="hidden" name="movie_category" value="serials">
        <input class="btn btn-primary" type="submit" value="Serials>">
    </form>
</span>
<div id="carouselExampleCaptions" class="main-movies carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner ">
        <div class="carousel-item active" data-bs-interval="2000">
            <img src="https://pluggedin.ru/images/cq2vq4wlkva-bigTopImage.jpg" class="new-movie-img" alt="...">
            <div class="carousel-caption d-md-block">
                <h1>New Films</h1>
            </div>
        </div>
        <c:forEach var="serial" items="${serial_list}">
            <div class="carousel-item" data-bs-interval="2000">
                <form action="${pageContext.request.contextPath}/controller.do">
                    <input type="hidden" name="command" value="movie_info_page_for_user"/>
                    <button type="submit" value="${serial.id}" name="id">
                        <img src="${serial.image_path}" class="d-block w-100 new-movie-img" alt="movie-image"/>
                    </button>
                </form>
                <div class="carousel-caption d-md-block">
                    <h5 class="movie-name" title="${serial.name}">${serial.name}</h5>
                    <div class="movie-rating">
                        <c:forEach begin="1" end="${serial.ratingvalue}" step="1">
                            <span style="font-size:20px;color:yellow;">&starf;</span>
                        </c:forEach>
                    </div>
                    <h5 class="movie-age-limit">${serial.ageLimit}+</h5>
                </div>
                <form action="${pageContext.request.contextPath}/controller.do" style="
                position: absolute;
                left: 54px;
                bottom: 5px;">
                    <input type="hidden" name="command" value="update_movie"/>
                    <input type="hidden" name="page" value="admin_page"/>
                    <button type="submit" value="${serial.id}" name="id" class="btn btn-outline-primary">
                        <i class="fa-solid fa-wrench"></i>
                    </button>
                </form>

                <form action="${pageContext.request.contextPath}/controller.do" style="
                           position: absolute;
                           bottom: 5px;
                           left: 102px;
                           ">
                    <input type="hidden" name="command" value="delete_movie"/>
                    <input type="hidden" name="page" value="admin_page"/>
                    <button type="submit" value="${serial.id}" name="id" class="btn btn-outline-danger">
                        <i class="fa-solid fa-trash"></i>
                    </button>
                </form>
            </div>
        </c:forEach>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Предыдущий</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Следующий</span>
    </button>
</div>

<span class="movie-title d-inline-block" tabindex="0" id="films" data-bs-toggle="tooltip" title="Отключенная подсказка">
    <form action="${pageContext.request.contextPath}/controller.do">
        <input type="hidden" name="command" value="movie_category_page">
        <input type="hidden" name="movie_category" value="films">
        <input class="btn btn-primary" type="submit" value="Films>">
    </form>
</span>
<div id="carouselExampleCaptions2" class="main-movies carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner ">
        <div class="carousel-item active" data-bs-interval="2000">
            <img src="https://pluggedin.ru/images/cq2vq4wlkva-bigTopImage.jpg" class="new-movie-img" alt="...">
            <div class="carousel-caption d-md-block">
                <h1>New Films</h1>
                <h5>Swipe to left <i class="fa-solid fa-angles-right"></i></h5>
            </div>
        </div>
        <c:forEach var="film" items="${film_list}">
            <div class="carousel-item" data-bs-interval="2000">
                <form action="${pageContext.request.contextPath}/controller.do">
                    <input type="hidden" name="command" value="movie_info_page_for_user"/>
                    <button type="submit" value="${film.id}" name="id">
                        <img src="${film.image_path}" class="d-block w-100 new-movie-img" alt="movie-image"/>
                    </button>
                </form>
                <div class="carousel-caption d-md-block">
                    <h5 class="movie-name" title="${film.name}">${film.name}</h5>
                    <div class="movie-rating">
                        <c:forEach begin="1" end="${film.ratingvalue}" step="1">
                            <span style="font-size:20px;color:yellow;">&starf;</span>
                        </c:forEach>
                    </div>
                    <h5 class="movie-age-limit">${film.ageLimit}+</h5>
                </div>
                <form action="${pageContext.request.contextPath}/controller.do" style="
                position: absolute;
                left: 54px;
                bottom: 5px;">
                    <input type="hidden" name="command" value="update_movie"/>
                    <input type="hidden" name="page" value="admin_page"/>
                    <button type="submit" value="${film.id}" name="id" class="btn btn-outline-primary">
                        <i class="fa-solid fa-wrench"></i>
                    </button>
                </form>

                <form action="${pageContext.request.contextPath}/controller.do" style="
                           position: absolute;
                           bottom: 5px;
                           left: 102px;
                           ">
                    <input type="hidden" name="command" value="delete_movie"/>
                    <input type="hidden" name="page" value="admin_page"/>
                    <button type="submit" value="${film.id}" name="id" class="btn btn-outline-danger">
                        <i class="fa-solid fa-trash"></i>
                    </button>
                </form>
            </div>
        </c:forEach>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions2" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Предыдущий</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions2" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Следующий</span>
    </button>
</div>

<span class="movie-title d-inline-block" tabindex="0" id="novelty" data-bs-toggle="tooltip"
      title="Отключенная подсказка">
    <form action="${pageContext.request.contextPath}/controller.do">
        <input type="hidden" name="command" value="movie_category_page">
        <input type="hidden" name="movie_category" value="serials">
        <input class="btn btn-primary" type="submit" value="Serials>">
    </form>
</span>
<div id="carouselExampleCaptions3" class="main-movies carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner ">
        <div class="carousel-item active" data-bs-interval="2000">
            <img src="https://pluggedin.ru/images/cq2vq4wlkva-bigTopImage.jpg" class="new-movie-img" alt="...">
            <div class="carousel-caption d-md-block">
                <h1>New Films</h1>
            </div>
        </div>
        <c:forEach var="serial" items="${serial_list}">
            <div class="carousel-item" data-bs-interval="2000">
                <form action="${pageContext.request.contextPath}/controller.do">
                    <input type="hidden" name="command" value="movie_info_page_for_user"/>
                    <button type="submit" value="${serial.id}" name="id">
                        <img src="${serial.image_path}" class="d-block w-100 new-movie-img" alt="movie-image"/>
                    </button>
                </form>
                <div class="carousel-caption d-md-block">
                    <h5 class="movie-name" title="${serial.name}">${serial.name}</h5>
                    <div class="movie-rating">
                        <c:forEach begin="1" end="${serial.ratingvalue}" step="1">
                            <span style="font-size:20px;color:yellow;">&starf;</span>
                        </c:forEach>
                    </div>
                    <h5 class="movie-age-limit">${serial.ageLimit}+</h5>
                </div>
                <form action="${pageContext.request.contextPath}/controller.do" style="
                position: absolute;
                left: 54px;
                bottom: 5px;">
                    <input type="hidden" name="command" value="update_movie"/>
                    <input type="hidden" name="page" value="admin_page"/>
                    <button type="submit" value="${serial.id}" name="id" class="btn btn-outline-primary">
                        <i class="fa-solid fa-wrench"></i>
                    </button>
                </form>

                <form action="${pageContext.request.contextPath}/controller.do" style="
                           position: absolute;
                           bottom: 5px;
                           left: 102px;
                           ">
                    <input type="hidden" name="command" value="delete_movie"/>
                    <input type="hidden" name="page" value="admin_page"/>
                    <button type="submit" value="${serial.id}" name="id" class="btn btn-outline-danger">
                        <i class="fa-solid fa-trash"></i>
                    </button>
                </form>
            </div>
        </c:forEach>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions3" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Предыдущий</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions3" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Следующий</span>
    </button>
</div>
<%-----------
<%-----------------------------Carousel---------------------------%>

<footer class="home-page-footer">
    <div class="card text-center" >
        <div class="card-header">
            Featured
        </div>
        <div class="card-body">
            <h5 class="card-title">Special title treatment</h5>
            <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
            <form action="${pageContext.request.contextPath}/controller.do">
                <input type="hidden" name="command" value="home_page"/>
                <input type="submit" class="nav-link-home" value="Home page"/>
            </form>
        </div>
        <div class="card-footer text-muted">
            2 days ago
        </div>
    </div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
        crossorigin="anonymous"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="alert/dist/sweetalert.css">
<script type="text/javascript">
    var login_status = document.getElementById("login_message").value;
    if (login_status == "login_success") {
        swal("Great", "You have successfully logged in", "success");
    }
</script>
</body>
</html>
