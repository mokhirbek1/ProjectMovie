<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/material-icon/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mainStyle.css">
    <style>
        <%@include file="/css/mainStyle.css" %>
    </style>
    <title>SignIn Form</title>
</head>
<body>
<input type="hidden" id="reg_message" value="<%= request.getAttribute("reg_message") %>"/>
<input type="hidden" id="login_message" value="<%= request.getAttribute("login_message") %>"/>
<div class="main">

    <!-- Sing in  Form -->
    <section class="sign-in">
        <div class="container">
            <div class="signin-content">
                <div class="signin-image">
                    <figure>
                        <img src="https://emblemsystems.com/wp-content/uploads/2020/04/3343839-1.jpg" alt="sing up image">
                    </figure>
                    <a href="${pageContext.request.contextPath}/pages/authenticate/registration.jsp" class="signup-image-link">Create an account</a>
                </div>

                <div class="signin-form">
                    <h2 class="form-title">Sign in</h2>
                    <form action="${pageContext.request.contextPath}/controller" class="register-form" id="login-form">
                        <input type="hidden" name="command" value="login">
                        <div class="form-group">
                            <label for="username"><i class="zmdi zmdi-account material-icons-name"></i></label>
                            <input type="text" name="username" id="username"pattern="[a-zA-Z0-9]+" placeholder="Your username" maxlength="10" minlength="4" required="required"/>
                        </div>
                        <div class="form-group">
                            <label for="password"><i class="zmdi zmdi-lock"></i></label>
                            <input type="password" name="password" id="password" pattern="[a-zA-Z0-9]+" maxlength="15" minlength="5" placeholder="Password" required="required"/>
                        </div>
                        <div class="form-group">
                           <a style="color: #0800ff" href="${pageContext.request.contextPath}/pages/authenticate/forgotPassword.jsp">Forgot password?</a>
                        </div>
                        <div class="form-group form-button">
                            <input type="submit" style="padding: 0; height: 33px;width: 65px;" id="signin" class="form-submit" value="Log in"/>
                        </div>
                    </form>
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
    var login_status = document.getElementById("login_message").value;
    if (status == "success") {
        swal("Done", "Account created successfully!", "success");
        // setTimeout(function(){
        // 	window.location.href = 'login.jsp';
        // }, 2000);
    }
    else if (login_status == "profile_blocked") {
        swal("Oops", "Sorry your profile is blocked", "error");
    }
    else if (login_status == "login_success") {
        swal("Oops", "You have successfully logged in", "success");
    }
    else if (login_status == "incorrect") {
        swal("Error", "Incorrect login or password", "error");
    }
</script>
</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>