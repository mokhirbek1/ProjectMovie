<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/js/all.min.js" integrity="sha512-6PM0qYu5KExuNcKt5bURAoT6KCThUmHRewN3zUFNaoI6Di7XJPTMoT6K0nsagZKk2OB4L7E3q1uQKHNHd4stIQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <style>
        <%@include file="/css/style.css" %>
    </style>
    <style>
        <%@include file="/css/navbar.css" %>
    </style>
    <title>Movies page</title>

</head>
<body style="background: aliceblue;">
<nav class="container-movie-navbar">
    <div class="container-navbar">
        <div class="nav-link-left" style="width: 360px;">
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
                <input type="submit" class="nav-link-home" style="font-size: 13px" value="Movies">
            </form>
        </div>
        <div class="nav-link-right-btn">
            <form  action="${pageContext.request.contextPath}/pages/main/admin/movies/movie_action/add.jsp">
                <button type="submit" class="nav-link-other nav-link-right-add">
                    <i class="fa-solid fa-plus"></i>
                    <span class="ms-2">Add Movie</span>
                </button>
            </form>
            <form  class="nav-link-logout" action="${pageContext.request.contextPath}/controller.do">
                <input type="hidden" name="command" value="logout">
                <input class="nav-link-logout" type="submit" value="Logout">
            </form>
        </div>
    </div>
</nav>
    <div class="container">
        <table class="table table-bordered border-primary table-hover">
            <thead class="bg-primary text-light">
            <tr>
                <th style="text-align:center;font-size: 13px;" scope="col">id</th>
                <th style="text-align:center;font-size: 13px;" scope="col">Name</th>
                <th style="text-align:center;font-size: 13px;" scope="col">Country</th>
                <th style="text-align:center;font-size: 13px;" scope="col">Created Year</th>
                <th style="text-align:center;font-size: 13px;" scope="col">Category</th>
                <th style="text-align:center;font-size: 13px;" scope="col">Language</th>
                <th style="text-align:center;font-size: 13px;" scope="col">Age Limit</th>
                <th style="text-align:center;font-size: 13px;" scope="col">Description</th>
                <th style="text-align:center;font-size: 13px;" scope="col">Image Path</th>
                <th style="text-align:center;font-size: 13px;" scope="col">Rating</th>
                <th style="text-align:center;font-size: 13px;" scope="col" class="table-actions">Actions</th>
            </tr>
            </thead>
            <tbody>
            <jsp:useBean id="movie_list" scope="request" type="java.util.List"/>
            <c:forEach var="movie" items="${movie_list}">
                <tr>
                    <td>${movie.id}</td>
                    <td>${movie.name}</td>
                    <td>${movie.country}</td>
                    <td>${movie.created_year}</td>
                    <td>${movie.category}</td>
                    <td>${movie.language}</td>
                    <td style="text-align:center;">${movie.age_limit}</td>
                    <td class="for_description">
                        <p>${movie.description}</p>
                    </td>
                    <td class="for_img_path">
                        <p>${movie.image_path}</p>
                    </td>
                    <td class="for_img_rating_value">
                        <p >${movie.rating_value}</p>
                    </td>
                    <td class="table-actions">
                        <div>
                            <form action="${pageContext.request.contextPath}/controller.do">
                                <input type="hidden" name="command" value="update_movie"/>
                                <input type="hidden" name="page" value="movies_page">
                                <button type="submit" value="${movie.id}" name="id" class="btn btn-outline-primary">
                                    <i class="fa-solid fa-wrench"></i>
                                </button>
                            </form>

                            <form action="${pageContext.request.contextPath}/controller.do">
                                <input type="hidden" name="command" value="delete_movie"/>
                                <button type="submit" value="${movie.id}" name="id" class="btn btn-outline-danger">
                                    <i class="fa-solid fa-trash"></i>
                                </button>
                            </form>
                        </div>

                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <!-- <tfoot>

            </tfoot> -->
        </table>
    </div>
</section>

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
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
</body>
</html>
