<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <style>
        <%@include file="/css/navbar.css" %>
    </style>
    <style>
        <%@include file="/css/movieCategoryPage.css" %>
    </style>
    <title>Category page</title>
</head>
<body>
<nav class="container-movie-navbar">
    <div class="container-navbar">
        <div class="nav-link-left" style="width: 453px;">
            <form action="${pageContext.request.contextPath}/controller.do">
                <input type="hidden" name="command" value="admin_page"/>
                <input type="submit" class="nav-link-other" style="width: 95px; font-size: 13px  " value="Admin page"/>
            </form>
            <form class="nav-link" action="${pageContext.request.contextPath}/controller.do">
                <input type="hidden" name="command" value="find_admins">
                <input type="submit" class="nav-link-other" value="Admins">
            </form>
            <form class="nav-link" action="${pageContext.request.contextPath}/controller.do">
                <input type="hidden" name="command" value="find_users">
                <input type="submit" class="nav-link-other" value="Users">
            </form>
            <form class="nav-link" action="${pageContext.request.contextPath}/controller.do">
                <input type="hidden" name="command" value="find_movies">
                <input type="submit"class="nav-link-other" value="Movies">
            </form>
            <div class="nav-link">
                <input type="submit" class="nav-link-home" style="font-size: 13px; width: 100px;" value="Category page">
            </div>
        </div>
        <form class="nav-link-logout" action="${pageContext.request.contextPath}/controller.do">
            <input type="hidden" name="command" value="logout">
            <input class="nav-link-logout" type="submit" value="Logout">
        </form>
    </div>
</nav>
<div class="movie-category">
    <input type="submit" class="nav-link-home" value="${movie_category} >"/>
</div>
<section class="movie-cards">
    <c:forEach var="movie" items="${movie_category_list}">
        <div class="movie-card">
            <img class="movie-card-img" src="${movie.image_path}">
            <div class="movie-card-bottom">
                <h2 class="movie-card-title">${movie.name}</h2>
                <h4 class="movie-card-subtitle">Country: ${movie.country}</h4>
                <h4 class="movie-card-subtitle">Language: ${movie.language}</h4>
                <h4 class="movie-card-subtitle">Age limit: ${movie.age_limit}+</h4>
                <h4 class="movie-card-subtitle">Rating: ${movie.rating_value}/10.0</h4>
                <c:forEach begin="1" end="${movie.rating_value}" step="1">
                    <span style="font-size:20px;color:#ffcb00;">&starf;</span>
                </c:forEach>
                <div class="movie-card-button">
                    <form action="${pageContext.request.contextPath}/controller.do">
                        <input type="hidden" name="command" value="movie_info_page"/>
                        <input type="hidden" name="id" value="${movie.id}"/>
                        <input type="submit" class="movie-card-btn" value="Show"/>
                    </form>
                </div>
            </div>
        </div>
    </c:forEach>
</section>

<div class="card text-center" >
    <div class="card-header">
        Featured
    </div>
    <div class="card-body">
        <h5 class="card-title">Special title treatment</h5>
        <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
        <form action="${pageContext.request.contextPath}/controller.do">
            <input type="hidden" name="command" value="admin_page"/>
            <input type="submit" class="nav-link-home" style="width: 95px; font-size: 13px  " value="Admin page"/>
        </form>
    </div>
    <div class="card-footer text-muted">
        2 days ago
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
</body>
</html>
