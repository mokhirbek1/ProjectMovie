<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <title>Support</title>
</head>
<body>
<div class="form-gap" style="padding-top: 70px"></div>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="text-center">
                        <h3>
                            <i class="fa fa-lock fa-4x"></i>
                        </h3>
                        <h1 style="color: #1a2226">${request.message}</h1>
                        <h2 class="text-center">Enter confirm password:</h2>
                        <c:if test="${request.message!=null}">
                            <p class="text-danger ml-1">${request.message}</p>
                        </c:if>
                        <c:if test="${requestScope.get('message')!=null}">
                            <p class="text-danger ml-1">${requestScope.get('message')}</p>
                        </c:if>

                        <div class="panel-body">
                            <form id="register-form" action="${pageContext.request.contextPath}/controller.do" >
                                <div class="form-group">
                                    <div class="input-group">
											<span class="input-group-addon">
                                                <i class="glyphicon glyphicon-envelope color-blue"></i>
                                            </span>
                                        <input type="hidden" name="command" value="validate_password">
                                        <input id="otp" name="confirm_password" placeholder="Enter confirm password" class="form-control" type="text" required="required">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <input name="recover-submit" class="btn btn-lg btn-primary btn-block" value="Reset Password" type="submit">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
