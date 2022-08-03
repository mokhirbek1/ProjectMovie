<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset='utf-8'>
    <meta name='viewport' content='width=device-width, initial-scale=1'>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
          integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/js/all.min.js"
            integrity="sha512-6PM0qYu5KExuNcKt5bURAoT6KCThUmHRewN3zUFNaoI6Di7XJPTMoT6K0nsagZKk2OB4L7E3q1uQKHNHd4stIQ=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <title>New password</title>
    <link
            href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css'
            rel='stylesheet'>
    <link
            href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css'
            rel='stylesheet'>
    <script type='text/javascript'
            src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
    <style>
        .bg-info {
            background-color: #ffd3d3 !important;
        }

        .form-horizontal {
            text-align: center;
        }

        .form-input-pass {
            border-radius: 5px;
            box-shadow: 0 0 5px rgb(0 0 0 / 20%);
            border: none;
            font-size: 13px;
            height: 25px;
            padding-left: 10px;
        }
    </style>
</head>
<body oncontextmenu='return false' class='snippet-body bg-info'>
<link rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-social/5.1.1/bootstrap-social.css">
<div>
    <input type="hidden" id="status" value="<%= request.getAttribute("status") %>"/>

    <!-- Container containing all contents -->
    <div class="container">
        <div class="row justify-content-center" style="
             width: 900px;
             margin: 20px auto;
            ">
            <div class="col-12 col-md-9 col-lg-7 col-xl-6 mt-5">
                <!-- White Container -->
                <div class="container bg-white rounded mt-2 mb-2 px-0">
                    <!-- Main Heading -->
                    <div class="row justify-content-center align-items-center pt-3">
                        <h1>
                            <strong>Reset Password</strong>
                        </h1>
                    </div>
                    <div class="pt-3 pb-3">
                        <form class="form-horizontal" action="${pageContext.request.contextPath}/controller.do">
                            <div class="form-group row justify-content-center px-3">
                                <div class="col-9 px-0">
                                    <input type="hidden" name="command" value="new_password">
                                    <label for="input-pass"><i class="fa-solid fa-key"></i></label>
                                    <input type="text" id="input-pass" name="password" placeholder="New Password"
                                           class="form-input-pass">
                                </div>
                            </div>
                            <!-- Password Input -->
                            <div class="form-group row justify-content-center px-3">
                                <div class="col-9 px-0">
                                    <label for="input-conf-pass"><i class="fa-solid fa-key"></i></label>
                                    <input type="password" id="input-conf-pass" name="confPassword"
                                           placeholder="Confirm New Password" class="form-input-pass">
                                </div>
                            </div>

                            <!-- Log in Button -->
                            <div class="form-group row justify-content-center">
                                <div class="col-3 px-3 mt-3">
                                    <input type="submit" value="Update" class="btn btn-block btn-info" style="
                                background-color: #153aff;
                                border-color: #153aff;
                                ">
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="mx-0 px-0 bg-light">
                        <div class="pt-2">
                            <div class="row justify-content-center" style="
                                width: 418px;
                                margin: 0 auto;
                                ">
                                <h5><span><a style="
                                font-weight: 300;
                                padding: 10px 0;
                                width: 180px;
                                margin: 0 auto;
                                " href="${pageContext.request.contextPath}/pages/jsp/authenticate/registration.jsp"
                                             class="text-solid"> Registration page</a></span>
                                </h5>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'
        src='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js'></script>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="alert/dist/sweetalert.css">
<script type="text/javascript">
    var status = document.getElementById("status").value;
    if (status == "resetFailed") {
        swal("Oops", "Some thing went wrong! Please try else", "error");
    }
    if (status == "notMatchPasswords") {
        swal("Oops", "New password and confirm password don't match, please will try again!", "error");
    }

</script>
</body>
</html>