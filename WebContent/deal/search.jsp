<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<c:if test="${userinfo eq null }">
	<script>
		alert("로그인이 필요한 페이지입니다.");
		location.href = "${root}";
	</script>
</c:if>
<c:if test="${userinfo ne null }">
<!DOCTYPE HTML>
<html>
	<head>
		<title>HAPPY HOUSE - Search</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
		 <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAH4mGtGTjkph4bcBxYyfERsJaegQgoIHA&callback=initMap"
        async defer></script>
        
		<link rel="stylesheet" href="${root}/assets/css/main.css" />
		<link rel="stylesheet" href="${root}/assets/css/mine.css" />
	</head>
	<body>
		<%@ include file="/user/confirm.jsp" %>
		<!-- Header -->
			<%@ include file="/board/header.jsp"%>
			<%@ include file="/deal/selectaddr.jsp"%>
		<!-- One -->
			<section id="one">
				<div class="inner">
					<header class="pb-5">
						<h2>거래 검색</h2>
						<div class="row grid-space-10">
                        <div class='col-12 justify-content-center' id="map" style="width: 800px; height: 400px"></div>
                    </div>
					</header>
					<div class="row">
						<div class="col-3">
							<div class="container">
								<form method="post" action="#">
									<div class="pb-3">
										<div class="pb-1">지역</div>
										<div class="select-wrapper pb-2">
											<select name="sido" id="sido">
												<option value="all" selected>도/광역시</option>
											</select>
										</div>
				            			<div class="select-wrapper pb-2">
				                			<select name="gugun" id="gugun">
				                    			<option value="all" selected>시/구/군</option>
				                			</select>
				            			</div>
				            			<div class="select-wrapper pb-2">
				                			<select name="cars" id="dong">
				                    			<option value="all" selected>동</option>
				                   			</select>
				            			</div>
									</div>
									<div class="pb-3">
										<div class="pb-1">세부 검색</div>
										<div class="form-group md">
				                			<input type="text" name="aptname" id="aptname" value="" placeholder="아파트 이름" />
				            			</div>
									</div>
									<div class="pb-3">
										<div class="pb-1">정렬</div>
										<div class="select-wrapper pb-2">
				                			<select name="gugun" id="gugun">
				                    			<option value="default" selected>최신 등록순</option>
				                    			<option value="high-price">높은 가격순</option>
				                    			<option value="lower-price">낮은 가격순</option>
				                			</select>
				            			</div>
									</div>
									<div class="pb-3">
										<input type="button" value="SEARCH" class="special" style="width:100%;" onclick="javascript:search();"/>
									</div>
								</form>
							</div>
						</div>	
						<div class="col-9">
							<div class="table-wrapper">
								<table>
									<thead>
										<tr>
											<th>No</th>
											<th>APT Name</th>
											<th>JiBun</th>
											<th>Price</th>
											<th>Date</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</section>

		<!-- Footer -->
			<%@ include file="/board/footer.jsp" %>
			
		<!-- Scripts -->
			<script src="${root}/assets/js/jquery.min.js"></script>
			<script src="${root}/assets/js/skel.min.js"></script>
			<script src="${root}/assets/js/util.js"></script>
			<script src="${root}/assets/js/main.js"></script>
			<script src="${root}/assets/js/map.js"></script>

	</body>
</html>
</c:if>