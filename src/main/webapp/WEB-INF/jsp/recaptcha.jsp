<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>
<div class="g-recaptcha" data-sitekey="6LebY3QUAAAAAPQ2hHWb3edUk9dSZXZffBNHua0a" data-callback="callback"></div>

<script>
	function callback(){
		console.log("callback!!! ${sitekey}");	
		var token = grecaptcha.getResponse();
		console.log("token : "+token);

		$.ajax({
			 url: "/verify?token="+token
			,type: "get"
			,success:function(result){
				console.log(result);
			}
			,error:function(e){
				console.log(e);
			}
		});
		
/* 		$("input[name=token]").val(grecaptcha.getResponse());		
		$("captchaForm").on("submit", function(event){
			console.log("submit callback!!");
			event.preventDefault();
		}); */
	}
</script>
<form name="captchaForm" action="/verify">
	<input type="hidden" name="token">
</form>
</body>
</html>