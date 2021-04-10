<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<body>
	<header id="header">
		<div class="inner">
			<a href="${root }" class="logo">HAPPY HOUSE</a>
			<nav id="nav">
				<a href="#">공지사항</a>
				<a href="${root}/main?act=news">오늘의 뉴스</a>
				<a href="#">정보 공유</a>
				<a href="#">관심 지역 설정</a>
				<a href="#">관심 지역 둘러보기</a>
			</nav>
		</div>
	</header>
	<a href="#menu" class="navPanelToggle"><span class="fa fa-bars"></span></a>
</body>
</html>