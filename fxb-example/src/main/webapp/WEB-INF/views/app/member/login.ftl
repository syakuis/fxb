<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Page Description">
	<meta name="author" content="syaku">
	<title>login</title>

	<link href="<@spring.url "/resources/node_modules/bootstrap/dist/css/bootstrap.min.css" />" rel="stylesheet">
	<link href="<@spring.url "/resources/node_modules/font-awesome/css/font-awesome.min.css" />" rel="stylesheet">

	<style>
		.form-signin .form-signin-heading,
		.form-signin .checkbox {
			margin-bottom: 10px;
		}
		.form-signin .checkbox {
			font-weight: normal;
			padding-left:20px;
		}
		.form-signin .form-control {
			height: auto;
			-webkit-box-sizing: border-box;
			-moz-box-sizing: border-box;
			box-sizing: border-box;
			padding: 10px;
			font-size: 16px;
		}
		.form-signin input[type="text"] {
			margin-bottom: -1px;
			border-bottom-right-radius: 0;
			border-bottom-left-radius: 0;
		}
		.form-signin input[type="password"] {
			margin-bottom: 10px;
			border-top-left-radius: 0;
			border-top-right-radius: 0;
		}
		<#if background?exists>
		.login {
			background: url(<@spring.url background />);
		}
		</#if>
	</style>
</head>
<body>
<div class="container">
	<div class="row login">
		<div class="col-md-4 col-md-offset-4">
			<!-- 로그인 -->
			<div>
				<form class="form-signin" role="form" method="post" action="${config.getString("security.loginProcessingUrl")}">
					<h2 class="form-signin-heading"><i class="fa fa-sign-in"></i> 로그인</h2>
					<input type="text" class="form-control" placeholder="아이디" id="username" name="username">
					<input type="password" class="form-control" placeholder="비밀번호" id="password" name="password">
					<div class="checkbox">
						<label class="checkbox-inline">
							<input type="checkbox" id="remember_user_id" value="Y"> 아이디 저장
						</label>
						<label class="checkbox-inline">
							<input type="checkbox" id="is_email" name="is_email" value="Y"> 메일계정 사용
						</label>
					</div>
					<button class="btn btn-lg btn-primary btn-block" type="submit">동기</button>
					<button class="btn btn-lg btn-primary btn-block" type="button">비동기</button>
				</form>
			</div>
		</div>
	</div>
</div>
<script src="<@spring.url "/resources/node_modules/jquery/dist/jquery.min.js" />"></script>
<script src="<@spring.url "/resources/node_modules/popper.js/dist/umd/popper.min.js" />"></script>
<script src="<@spring.url "/resources/node_modules/bootstrap/dist/js/bootstrap.min.js" />"></script>
<script type="text/javascript">
	var csrf = '<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />';
	$('.form-signin').submit(function() {
		$(this).append(csrf);
	});
</script>
</body>
</html>