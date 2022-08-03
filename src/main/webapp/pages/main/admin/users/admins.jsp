<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminManageUsersPage.css">
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
    <title>Admins page</title>
</head>
<body style="background: aliceblue;">
<input type="hidden" id="login_message" value="<%= request.getAttribute("login_message") %>"/>

<nav class="container-movie-navbar">
    <div class="container-navbar">
        <div class="nav-link-left" style="width: 365px;">
            <form action="${pageContext.request.contextPath}/controller.do">
                <input type="hidden" name="command" value="admin_page"/>
                <input type="submit" class="nav-link-other" value="Admin page"/>
            </form>
            <form action="${pageContext.request.contextPath}/controller.do">
                <input type="hidden" name="command" value="find_admins">
                <input type="submit" class="nav-link-home" style="width: 95px; font-size: 13px" value="Admins">
            </form>
            <form action="${pageContext.request.contextPath}/controller.do">
                <input type="hidden" name="command" value="find_users">
                <input type="submit" class="nav-link-other" style="width: 95px; font-size: 13px" value="Users">
            </form>
            <form action="${pageContext.request.contextPath}/controller.do">
                <input type="hidden" name="command" value="find_movies">
                <input type="submit" class="nav-link-other" value="Movies">
            </form>
        </div>
        <form class="nav-link-logout" action="${pageContext.request.contextPath}/controller.do">
            <input type="hidden" name="command" value="logout">
            <input class="nav-link-logout" type="submit" value="Logout">
        </form>
    </div>
</nav>
<section class="main-wrapper">
    <div class="container py-4">
        <table class="table table-bordered border-primary table-hover">
            <thead class="bg-primary text-light">
            <tr>
                <th style="text-align:center;" scope="col">id</th>
                <th style="text-align:center;" scope="col">Email</th>
                <th style="text-align:center;" scope="col">Role</th>
                <th style="text-align:center;" scope="col">Firstname</th>
                <th style="text-align:center;" scope="col">Lastname</th>
                <th style="text-align:center;" scope="col">Username</th>
                <th style="text-align:center;" scope="col">Status</th>
                <th style="text-align:center; width: 110px" scope="col" class="table-actions">Action</th>
            </tr>
            </thead>
            <tbody>
            <jsp:useBean id="admins" scope="request" type="java.util.List"/>
            <c:forEach var="admin" items="${admins}">
                <tr>
                    <td style="text-align:center; width: 44px;">${admin.id}</td>
                    <td style="text-align:center; width: 180px;">${admin.email}</td>
                    <td style="text-align:center; width: 70px;">${admin.role}</td>
                    <td style="text-align:center; width: 110px;">${admin.firstname}</td>
                    <td style="text-align:center; width: 120px;">${admin.lastname}</td>
                    <td style="text-align:center; width: 80px;">${admin.userName}</td>
                    <td style="text-align:center; width: 70px;">${admin.status}</td>
                    <td style="text-align:center;">
                        <div class="users-action-btn" style="justify-content: space-between; width: 88px;">
                            <c:if test="${userid<admin.id}">
                                <c:if test="${admin.status.toString().equals('BLOCKED')}">
                                    <form action="${pageContext.request.contextPath}/controller.do">
                                        <input type="hidden" name="command" value="activate_user">
                                        <input type="hidden" name="page" value="admins_page"/>
                                        <button type="submit" value="${admin.id}" name="id"
                                                class="btn btn-outline-success"><i class="fa-solid fa-unlock"></i>
                                        </button>
                                    </form>
                                </c:if>
                                <c:if test="${admin.status.toString().equals('ACTIVE')}">
                                    <form action="${pageContext.request.contextPath}/controller.do">
                                        <input type="hidden" name="command" value="block_user">
                                        <input type="hidden" name="page" value="admins_page"/>
                                        <button type="submit" name="id" value="${admin.id}"
                                                class="btn btn-outline-danger"><i class="fa-solid fa-lock"></i></button>
                                    </form>
                                </c:if>
                                <form action="${pageContext.request.contextPath}/controller.do">
                                    <input type="hidden" name="command" value="change_user_role">
                                    <input type="hidden" name="role" value="user">
                                    <input type="hidden" name="page" value="admin_page">
                                    <button type="submit" value="${admin.id}" name="user_id"
                                            class="btn btn-outline-dark">
                                        <i class="fa-solid fa-trash"></i></button>
                                </form>
                            </c:if>
                        </div>
                        <c:if test="${userid>admin.id}">
                            <div class="users-action-last">
                                <div style="color: #1a2226; text-align:center;">
                                    <i class="fa-solid fa-user-shield"></i></div>
                            </div>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</section>
<footer class="home-page-footer">
    <div class="card text-center" style="position: absolute;width: 100%;bottom: 0;">
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
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="alert/dist/sweetalert.css">
<script type="text/javascript">
    var login_status = document.getElementById("login_message").value;
    else if (login_status == "login_success") {
        swal("Great", "You have successfully logged in", "success");
    }
</script>
</body>
</html>

