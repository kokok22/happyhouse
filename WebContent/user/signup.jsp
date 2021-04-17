<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
	<head>
		<title>HAPPY HOUSE - Sign up</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<link rel="stylesheet" href="${root}/assets/css/main.css" />
		<link rel="stylesheet" href="${root}/assets/css/mine.css" />
	</head>
	<body>
		<!-- Header -->
			<%@ include file="../board/header.jsp"%>
			<%@ include file="../user/duplicateCheck.jsp"%>

		<!-- Main -->
			<section class="container 50% py-5">
				<div class="py-5"><h2 align="center">SIGN UP</h2></div>
				<c:if test="${ msg ne null }">
					<div class="12u$">
						<div class="container warning" align="center">${msg}</div>
					</div>
				</c:if>
				<form id="signupform" class="mt-4" method="post" action="#">
					<input type="hidden" name="act" value="signup" />
					<div class="row uniform 50%">
						<div class="9u 12u$">
							<input type="text" name="userId" id="userId" value="" placeholder="아이디" />
							<input type="hidden" id="chk" value="false" />
							<div class="container warning" id="idwar">중복확인 필수</div>
						</div>
						<div class="2u 12u$">
							<input type="button" name="idchk" id="idchk" value="중복 확인" onclick="javascript:check();"/>
						</div>
						<div class="12u$">
							<input type="password" name="userPwd" id="userPwd" value="" placeholder="비밀번호" />
							<div class="container warning" id="pwdwar">비밀번호는 6자리 이상이어야 합니다.</div>
						</div>
						<div class="12u$">
							<input type="password" name="userPwdchk" id="userPwdchk" value="" placeholder="비밀번호 확인" />
							<div class="container warning" id="pwdchkwar">비밀번호가 일치하지 않습니다.</div>
						</div>
						<div class="6u 12u$">
							<input type="text" name="lastName" id="lastName" value="" placeholder="성" />
						</div>
						<div class="6u 12u$">
							<input type="text" name="firstName" id="firstName" value="" placeholder="이름" />
						</div>
						<div class="12u$">
							<input type="text" name="email" id="email" value="" placeholder="이메일" />
							<div class="container warning" id="emailwar">이메일 형식이 아닙니다.</div>
						</div>
						<div class="9u 12u$">
							<input type="text" name="postcode" id="postcode" placeholder="우편번호" readonly>
						</div>
						<div class="2u 12u$">
							<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
						</div>
						<div class="12u$">
							<input type="text" id="address" placeholder="주소" name="address" readonly>
						</div>
						<div class="6u 12u$">
							<input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소">
						</div>
						<div class="6u 12u$">
							<input type="text" id="extraAddress" placeholder="참고항목" readonly>
						</div>
						<div class="12u$ pb-5">
							<input type="button" value="SUBMIT" class="special" style="width:100%;" onclick="javascript:signup();"/>
						</div>
					</div>
				</form>
			</section>

		<!-- Footer -->
			<%@ include file="../board/footer.jsp" %>

		<!-- Scripts -->
			<script src="${root}/assets/js/validationCheck.js"></script>
			<script src="${root}/assets/js/address.js"></script>
			<script src="${root}/assets/js/jquery.min.js"></script>
			<script src="${root}/assets/js/skel.min.js"></script>
			<script src="${root}/assets/js/util.js"></script>
			<script src="${root}/assets/js/main.js"></script>

	</body>
</html>