<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
	<head>
		<title>HAPPY HOUSE - Forgot Password</title>
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
	<script type="text/javascript">
		function find() {
			if($("#useId").val() == "") {
				alert("아이디를 입력해주세요.");
				return;
			} else if($("#email").val() == "") {
				alert("이메일을 입력해주세요.");
				return;
			}  else if(!CheckEmail($("#email").val())) {
				alert("이메일 형식으로 입력해주세요.");
				return;
			} else {
				$("#findform").attr("action", "${root}/main").submit();
			}
		}
	</script>
	
	<body>
		<!-- Header -->
			<%@ include file="../board/header.jsp"%>

		<!-- Main -->
			<section class="container 50% py-5">
				<div class="py-5"><h2 align="center">FIND PASSWORD</h2></div>
				<c:if test="${ msg ne null }">
					<div class="12u$">
						<div class="container warning" align="center">${msg}</div>
					</div>
				</c:if>
				<form id="findform" class="mt-4" method="post" action="#">
					<input type="hidden" name="act" value="find" />
					<div class="row uniform 50%">
						<div class="12u$">
							<input type="text" name="userId" id="userId" value="${userinfo.userId}" placeholder="아이디"/>
						</div>
						<div class="12u$">
							<input type="text" name="email" id="email" value="${userinfo.email}" placeholder="이메일" />
						</div>
						<div class="12u$ pb-5">
							<input type="button" value="SUBMIT" class="special" style="width:100%;" onclick="javascript:find();"/>
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