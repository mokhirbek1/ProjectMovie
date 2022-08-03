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
</head>
<body>
<nav class="container-movie-navbar">
    <div class="container-navbar">
        <form action="${pageContext.request.contextPath}/controller.do">
            <input type="hidden" name="command" value="home_page"/>
            <input type="submit" class="nav-link-home" value="Home page"/>
        </form>
        <form action="${pageContext.request.contextPath}/controller.do">
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
                <input type="hidden" name="command" value="movie_rating_for_user"/>
                <div class="rate">
                    <input type="radio" id="star10" name="rate" value="10"/>
                    <label for="star10" title="text"></label>
                    <input type="radio" id="star9" name="rate" value="9"/>
                    <label for="star9" title="text"></label>
                    <input type="radio" id="star8" name="rate" value="8"/>
                    <label for="star8" title="text"></label>
                    <input type="radio" id="star7" name="rate" value="7"/>
                    <label for="star7" title="text"></label>
                    <input type="radio" id="star6" name="rate" value="6"/>
                    <label for="star6" title="text"></label>
                    <input type="radio" id="star5" name="rate" value="5"/>
                    <label for="star5" title="text"></label>
                    <input type="radio" id="star4" name="rate" value="4"/>
                    <label for="star4" title="text"></label>
                    <input type="radio" id="star3" name="rate" value="3"/>
                    <label for="star3" title="text"></label>
                    <input type="radio" id="star2" name="rate" value="2"/>
                    <label for="star2" title="text"></label>
                    <input type="radio" id="star1" name="rate" value="1"/>
                    <label for="star1" title="text"></label>
                </div>
                <button type="submit" name="id" value="${movie_list.id}" class="btn btn-primary">Access</button>
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
    <form class="second-part-movie-post" action="${pageContext.request.contextPath}/controller.do">
        <input type="hidden" name="command" value="comment_post"/>
        <label for="inputComment" class="form-label">You can comment</label>
        <textarea required rows="3" cols="10" id="inputComment" class="form-control" name="comment"></textarea>
        <button type="submit" name="id" value="${movie_list.id}" class="btn btn-primary mt-3">Post comment</button>
    </form>
</div>


<div class="comments-wrapper">
    <jsp:useBean id="comment_list" scope="request" type="java.util.List"/>
    <c:forEach var="temp_comment" items="${comment_list}">
        <div class="comment">
            <div class="comment-avatar">
        <span class="user-profile text-dark bg-light text-decoration-none p-2 rounded-circle">
          <i class="fa-regular fa-user"></i>
        </span>
            </div>
            <div class="comment-text">
                <h5>${temp_comment.username}</h5>
                    ${temp_comment.comment_text}
            </div>
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
                <input type="hidden" name="command" value="home_page"/>
                <input type="submit" class="nav-link-home" value="Home page"/>
            </form>
        </div>
        <div class="card-footer text-muted">
            2 days ago
        </div>
    </div>
</footer>
</body>
</html>
