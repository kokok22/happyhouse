<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<c:set var="root" value="${pageContext.request.contextPath}"/>
<c:if test="${userinfo ne null }">
	<div class="p-2" align="right">
		<a href="${root}/main?act=mvmodify"><strong>${userinfo.lastName}${userinfo.firstName}</strong></a>님 환영합니다.
		<a class="m-2" href="${root}/main?act=logout">로그아웃</a>
	</div>
</c:if>
<c:if test="${userinfo eq null}">
	<div class="p-2" align="right">
		<a class="m-2" href="${root}/main?act=mvlogin">로그인</a>
		<a class="m-2" href="${root}/main?act=mvsignup">회원가입</a>
	</div>
</c:if>