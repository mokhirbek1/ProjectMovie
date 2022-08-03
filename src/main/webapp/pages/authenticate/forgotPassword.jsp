<!doctype html>
<html>
<head>
<meta charset='utf-8'>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>Forgot Password</title>
<link
	href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css'
	rel='stylesheet'>
<link href='' rel='stylesheet'>
<script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
<style>
body {
	background-position: center;
	background-color: #eee;
	background-repeat: no-repeat;
	background-size: cover;
	color: #505050;
	font-family: "Rubik", Helvetica, Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	line-height: 1.5;
	text-transform: none
}

.padding-bottom-3x {
	padding-bottom: 72px !important
}

.card-footer {
	background-color: #fff
}

.btn {
	font-size: 13px
}

.form-control:focus {
	color: #495057;
	background-color: #fff;
	border-color: #76b7e9;
	outline: 0;
	box-shadow: 0 0 0 0px #28a745
}
</style>
</head>
<body oncontextmenu='return false' class='snippet-body'>
	<div class="container padding-bottom-3x mb-2 mt-5">
		<div class="row justify-content-center">
			<div class="col-lg-8 col-md-10">

				<form class="card mt-4" style="border-radius: 10px; width: 303px; margin: 150px auto" action="${pageContext.request.contextPath}/controller.do">
					<div class="card-body">
						<div class="form-group">
							<label for="email-for-pass">Enter your email address and username</label>
							<input type="hidden" name="command" value="forgot_password">
							<input class="form-control mb-2" type="text" name="email" id="email-for-pass" required="required" placeholder="enter email"/>
							<small class="form-text text-muted">Enter the registered email address. Then we'll send new password to your email to restore your account.</small>
						</div>
					</div>
					<div class="card-footer" style="
					background-color: #fff;
					display: flex;
    				justify-content: space-between;
    				align-items: center;">
						<button class="btn btn-success" type="submit">Get New Password</button>
						<a class="btn btn-danger" style="
						color: white;
						border-radius: 5px;
						box-shadow: 0 0 5px rgb(0 0 0 / 20%);"
						href="${pageContext.request.contextPath}/pages/authenticate/login.jsp">Back to login</a>
					</div>
				</form>

			</div>
		</div>
	</div>
	<script type='text/javascript'
		src='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js'></script>
	<script type='text/javascript' src=''></script>
	<script type='text/javascript' src=''></script>
	<script type='text/Javascript'></script>
</body>
</html>