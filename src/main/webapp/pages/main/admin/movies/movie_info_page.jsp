<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
            integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
          integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/js/all.min.js"
            integrity="sha512-6PM0qYu5KExuNcKt5bURAoT6KCThUmHRewN3zUFNaoI6Di7XJPTMoT6K0nsagZKk2OB4L7E3q1uQKHNHd4stIQ=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <title>Movie info</title>
    <jsp:useBean id="movie_list" scope="request" type="by.makhkamov.movie.entity.Movie"/>
    <jsp:useBean id="rating_info" scope="request" type="by.makhkamov.movie.entity.Rating"/>
    <title>Id Page</title>
</head>
<body>
<nav class="container-movie-navbar">
    <div class="container-navbar">
        <div class="nav-link-left" style="width: 370px;">
            <form class="nav-link" style="padding: 0" action="${pageContext.request.contextPath}/controller.do">
                <input type="hidden" name="command" value="admin_page"/>
                <input type="submit" class="nav-link-other" style="width: 95px" value="Admin page"/>
            </form>
            <form class="nav-link" style="padding: 0" action="${pageContext.request.contextPath}/controller.do">
                <input type="hidden" name="command" value="find_users">
                <input type="submit" class="nav-link-other" value="Users">
            </form>
            <form class="nav-link" style="padding: 0" action="${pageContext.request.contextPath}/controller.do">
                <input type="hidden" name="command" value="find_movies">
                <input type="submit"class="nav-link-other" value="Movies">
            </form>
            <form class="nav-link" style="padding: 0">
                <input type="submit" style="width: 100px; font-size: 13px" class="nav-link-home" value="Movie info page">
            </form>
        </div>
        <form class="nav-link-logout" action="#">
            <input type="hidden" name="command" value="logout">
            <input class="nav-link-logout" type="submit" value="Logout">
        </form>
    </div>
</nav>
<div class="container-movie-post">

    <div class="first-part-movie-post">

        <div class="movie-img-and-rating">
            <div class="movie-post-title"><h4>
                <jsp:getProperty name="movie_list" property="name"/>
            </h4></div>
            <img class="movie-post-img" src="<jsp:getProperty name="movie_list" property="image_path"/>"
                 alt="movie-image">
            <form action="${pageContext.request.contextPath}/controller.do" class="movie-rating">
                <input type="hidden" name="command" value="movie_rating"/>
                <c:forEach begin="1" end="${rating_info.value}" step="1">
                    <span style="font-size:20px;color:yellow;">&starf;</span>
                </c:forEach>
            </form>
        </div>

        <div class="movie-post-info">
            <p class="post-info-title">Category:
                <jsp:getProperty name="movie_list" property="genre"/>
            </p>
            <p class="post-info-title">Created year:
                <jsp:getProperty name="movie_list" property="createdYear"/>
            </p>
            <p class="post-info-title">Country:
                <jsp:getProperty name="movie_list" property="country"/>
            </p>
            <p class="post-info-title">Rating:
                <jsp:getProperty name="movie_list" property="ratingvalue"/>
            </p>
            <p class="post-info-title">Аge restriction:
                <jsp:getProperty name="movie_list" property="ageLimit"/>
            </p>
        </div>

    </div>
    <p class="movie-description-title">Description</p>
    <div class="movie-description">
        <p class="movie-description-info">
            <jsp:getProperty name="movie_list" property="description"/>
        </p>
    </div>
</div>


<div class="comments-wrapper">
    <jsp:useBean id="comment_list" scope="request" type="java.util.List"/>
    <c:forEach var="comment_list" items="${comment_list}">
        <div class="comment">
            <div class="comment-avatar">
        <span class="user-profile text-dark bg-light text-decoration-none p-2 rounded-circle">
          <i class="fa-regular fa-user"></i>
        </span>
            </div>
            <div class="comment-text">
                <h5>${comment_list.username}</h5>
                    ${comment_list.comment_text}
                ${request.movie_id}
            </div>
            <form action="${pageContext.request.contextPath}/controller.do">
                <input type="hidden" name="command" value="delete_comment"/>
                <input type="hidden" name="comment_text" value="${comment_list.comment_text}">
                <input type="hidden" name="id" value="${requestScope.get("id")}">
                <button type="submit" value="${comment_list.id}" name="comment_id" class="btn btn-outline-danger">
                    <i class="fa-solid fa-trash"></i>
                </button>
            </form>
        </div>
    </c:forEach>
</div>

<footer class="home-page-footer">
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
</footer>
</body>
</html>
