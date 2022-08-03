<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/js/all.min.js" integrity="sha512-6PM0qYu5KExuNcKt5bURAoT6KCThUmHRewN3zUFNaoI6Di7XJPTMoT6K0nsagZKk2OB4L7E3q1uQKHNHd4stIQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!-- Font Icon -->
    <link rel="stylesheet"
    href="${pageContext.request.contextPath}/fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mainStyle.css">
    <style>
        <%@include file="/css/mainStyle.css"%>
    </style>
    <title>SignUp</title>
</head>
<body>
<input type="hidden" id="reg_message" value="<%= request.getAttribute("reg_message") %>"/>

<div class="main">
    <!-- Sign up form -->
    <section class="signup">
        <div class="container">
            <div class="signup-content">
                <div class="signup-form">
                    <h2 class="form-title">Sign up</h2>

                    <form method="post" action="${pageContext.request.contextPath}/controller.do" class="register-form"
                          id="register-form">
                        <input type="hidden" name="command" value="registration">
                        <div class="form-group">
                            <label for="email"><i class="zmdi zmdi-email"></i></label>
                            <input type="email" name="email" id="email" placeholder="Email" required="required"/>
                        </div>
                        <div class="form-group">
                            <label for="firstname"><i class="fa-solid fa-user"></i></label>
                            <input type="text" name="firstname" id="firstname" placeholder="Firstname" required="required"/>
                        </div>
                        <div class="form-group">
                            <label for="lastname"><i class="fa-regular fa-user"></i></label>
                            <input type="text" name="lastname" id="lastname" placeholder="Lastname" required="required"/>
                        </div>
                        <div class="form-group">
                            <label for="username"><i class="fa-solid fa-user-pen"></i></label>
                            <input type="text" name="username" id="username" placeholder="Username or Login" required="required"/>
                        </div>
                        <div class="form-group">
                            <label for="password"><i class="zmdi zmdi-lock"></i></label>
                            <input type="password" name="password" id="password" placeholder="Password" required="required"/>
                        </div>
                        <div class="form-group">
                            <label for="re_password"><i class="zmdi zmdi-lock-outline"></i></label>
                            <input type="password" name="re_password" id="re_password" placeholder="Repeat your password" required="required"/>
                        </div>
                        <div class="form-group form-button">
                            <input type="submit" name="signup" id="signup" class="form-submit" style="height: 33px; padding: 0; width: 75px;" value="Register"/>
                        </div>
                    </form>
                </div>
                <div class="signup-image">
                    <figure>
                        <img style="margin-top: 45px" src="https://static.vecteezy.com/system/resources/previews/003/512/526/original/content-manager-concept-isolated-creation-of-content-for-filling-site-layout-people-scene-in-flat-cartoon-design-illustration-for-blogging-website-mobile-app-promotional-materials-vector.jpg" alt="sing up image">
                    </figure>
                    <a style="width: 450px;" href="${pageContext.request.contextPath}/pages/authenticate/login.jsp"
                       class="signup-image-link">I am already user</a>
                </div>
            </div>
        </div>
    </section>


</div>
<!-- JS -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="js/main.js"></script>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="alert/dist/sweetalert.css">

<script type="text/javascript">
    var status = document.getElementById("reg_message").value;
    if (status == "success") {
        swal("Done", "Account created successfully!", "success");
        // setTimeout(function(){
        // 	window.location.href = 'login.jsp';
        // }, 2000);
    }
    if (status == "not_matches_passwords") {
        swal("Error", "You wrote don't matches passwords, please try again", "error");
    }
    if (status == "exist_email") {
        swal("Error", "You wrote exist email, please will write another email", "error");
    }
    if (status == "exist_login") {
        swal("Error", "You wrote exist login, please will write another login", "error");
    }

</script>
</body>
</html>
