<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/actionMovie.css">
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
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <title>Add Page</title>
</head>
<body>
<nav class="container-movie-navbar">
    <div class="container-navbar">
        <div class="nav-link-left" style="width: 450px;">
            <form action="${pageContext.request.contextPath}/controller.do">
                <input type="hidden" name="command" value="admin_page"/>
                <input type="submit" class="nav-link-other" style="width: 95px" value="Admin page"/>
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
                <input type="submit" class="nav-link-other" value="Movies">
            </form>
            <form class="nav-link" action="${pageContext.request.contextPath}/pages/main/admin/movies/movie_action/add.jsp">
                <input type="submit" style="width: 95px; font-size: 13px;" class="nav-link-home" value="Add Movie">
            </form>
        </div>
        <form class="nav-link-logout" action="${pageContext.request.contextPath}/controller.do">
            <input type="hidden" name="command" value="logout">
            <input class="nav-link-logout" type="submit" value="Logout">
        </form>
    </div>
</nav>
<section class="movie-registration">
    <div class="movie-registration-title">Movie registration</div>
    <form action="${pageContext.request.contextPath}/controller.do" class="movie-registration-form">
        <input type="hidden" name="command" value="add_movie"/>
        <input class="movie-registration-input" type="text" placeholder="movie name" required name="movieName">
        <input class="movie-registration-input" type="text" placeholder="country name" required name="countryName">
        <input class="movie-registration-input" type="text" placeholder="created year"required name="createdYear">
        <div class="input-group mb-3 movie-registration-input">
            <select name="category" class="form-select">
                <option class="movie-registration-option" selected>choose category...</option>
                <option value="new">new</option>
                <option value="best">best</option>
                <option value="adventure">adventure</option>
                <option value="serials">serials</option>
                <option value="film">film</option>
                <option value="comedy">comedy</option>
                <option value="cartoon">cartoon</option>
            </select>
        </div>
        <div class="input-group mb-3 movie-registration-input">
            <select name="language" class="form-select">
                <option class="movie-registration-option" selected>choose language...</option>
                <option value="russian">russian</option>
                <option value="english">english</option>
            </select>
        </div>
        <input class="movie-registration-input" type="text" placeholder="age limit"required name="ageLimit">
        <textarea rows="5" cols="50" class="movie-registration-textarea" name="description"required placeholder="description"></textarea>
        <input class="movie-registration-input" type="text" placeholder="image(800x300) url"required name="imagePath">
        <button type="submit" class="nav-link-home movie-registration-btn" style="width: 95px">Save</button>
    </form>
</section>

<footer class="home-page-footer">
    <div class="card text-center">
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
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
        crossorigin="anonymous"></script>
</body>
</html>