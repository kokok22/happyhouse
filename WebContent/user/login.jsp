<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<!-- 쿠키 검사 -->
<c:if test="${cookie.save_id.value ne null}">
	<c:set var="saveid" value="${ cookie.save_id.value }"/>
	<c:set var="idck" value=" checked=\"checked\""/>
</c:if>

<!DOCTYPE html>
<html>
	<head>
		<title>HAPPY HOUSE - Log in</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="${root}/assets/css/main.css" />
		<link rel="stylesheet" href="${root}/assets/css/mine.css" />
	</head>
	<script type="text/javascript">
		function login() {
			if($("#userId").val() == "") {
				alert("아이디를 입력해주세요.");
				return;
			} else if($("#userPwd").val() == "") {
				alert("비밀번호를 입력해주세요.");
				return;
			} else {
				$("#loginform").attr("action", "${root}/main").submit();
			}
		}
	</script>
	
	<body>
		<!-- Header -->
			<%@ include file="../board/header.jsp"%>

		<!-- Main -->
			<section class="container 50% py-5">
				<div class="pt-5 pb-4"><h2 align="center">HAPPY HOUSE</h2></div>
				<form id="loginform" class="mt-4" method="post" action="#">
					<input type="hidden" name="act" value="login" />
					<div class="row uniform 50%">
					<c:if test="${ msg ne null }">
						<div class="12u$">
							<div class="container warning" align="center">${msg}</div>
						</div>
					</c:if>
						<div class="12u$">
							<input type="text" name="userId" id="userId" value="${saveid}" placeholder="아이디" />
						</div>
						<div class="12u$">
							<input type="password" name="userPwd" id="userPwd" value="" placeholder="비밀번호" />
						</div>
						<div class="12u$">
							<input type="button" value="LOG IN" class="special" style="width:100%;" onclick="javascript:login();"/>
						</div>
						<div class="4u 12u$(xsmall)">
							<input type="checkbox" id="priority-low" value="saveok" name="idsave" ${idck}>
							<label for="priority-low">아이디 저장</label>
						</div>
						<div align="right" class="8u 12u$(xsmall)">
							<a href="${root}/main?act=mvforgot">비밀번호 찾기</a>
						</div>
					</div>
				</form>
			</section>

		<!-- Footer -->
			<%@ include file="/board/footer.jsp" %>

		<!-- Scripts -->
			<script src="${root}/assets/js/jquery.min.js"></script>
			<script src="${root}/assets/js/skel.min.js"></script>
			<script src="${root}/assets/js/util.js"></script>
			<script src="${root}/assets/js/main.js"></script>

	</body>
</html>