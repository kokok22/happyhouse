<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
function check(){
	$.get("${pageContext.request.contextPath}/main"
			,{act:"idcheck", id:$("#userId").val()}
			,function(data, status){
				if(data.length>0){
					$("#idwar").text("사용가능한 아이디 입니다.");
					$("#chk").val("true");
				}
				else{
					$("#idwar").text("중복된 아이디입니다.");
				}
			}//function
			, "json"
	);//get
}

function signup() {
	if($("#userId").val() == "") {
		alert("아이디를 입력해주세요.");
		return;
	} else if($("#chk").val() == "false") {
		alert("아이디 중복확인 해주세요.");
		return;
	} else if($("#userPwd").val() == "") {
		alert("비밀번호를 입력해주세요."); 
		return;
	}else if($("#userPwdchk").val() != $("#userPwd").val()) {
		alert("비밀번호가 일치하지 않습니다.");
		return;
	}  else if($("#lastName").val() == "") {
		alert("성을 입력해주세요.");
		return;
	}  else if($("#firstName").val() == "") {
		alert("이름을 입력해주세요.");
		return;
	}  else if($("#email").val() == "") {
		alert("이메일을 입력해주세요.");
		return;
	}  else if(!CheckEmail($("#email").val())) {
		alert("이메일 형식으로 입력해주세요.");
		return;
	}  else if($("#address").val() == "") {
		alert("주소를 입력해주세요.");
		return;
	} else {
		$("#signupform").attr("action", "${root}/main").submit();
	}
}


</script>
</head>
<body>
</body>
</html>