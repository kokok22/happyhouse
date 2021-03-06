<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
let colorArr = ['table-primary','table-success','table-danger'];

$(document).ready(function(){
	$.get("${pageContext.request.contextPath}/map"
		,{act:"sido"}
		,function(data, status){
			$.each(data, function(index, vo) {
				$("#sido").append("<option value='"+vo.sido_code+"'>"+vo.sido_name+"</option>");
			});//each
		}//function
		, "json"
	);//get
});//ready
$(document).ready(function(){
	$("#sido").change(function() {
		$.get("${pageContext.request.contextPath}/map"
				,{act:"gugun", sido:$("#sido").val()}
				,function(data, status){
					$("#gugun").empty();
					$("#gugun").append('<option value="0">시/구/군</option>');
					$.each(data, function(index, vo) {
						$("#gugun").append("<option value='"+vo.gugun_code+"'>"+vo.gugun_name+"</option>");
					});//each
				}//function
				, "json"
		);//get
	});//change
	$("#gugun").change(function() {
		$.get("${pageContext.request.contextPath}/map"
				,{act:"dong", gugun:$("#gugun").val()}
				,function(data, status){
					$("#dong").empty();
					$("#dong").append('<option value="0">동</option>');
					$.each(data, function(index, vo) {
						$("#dong").append("<option value='"+vo.dong+"'>"+vo.dong+"</option>");
					});//each
				}//function
				, "json"
		);//get
	});//change
});//ready

function search(){
	$.get("${pageContext.request.contextPath}/map"
			,{act:"apt", dong:$("#dong").val(), aptname:$("#aptname").val(), sort:$("#sort").val() }
			,function(data, status){
				$("tbody").empty();
				if(data.length==0){
					let str = "<tr><td colspan='5'><center>저장된 거래 데이터가 없습니다.<center></td></tr>";
					$("tbody").append(str);
				}
				else
					$.each(data, function(index, vo) {
						let str = "<tr>"
						+ "<td>" + (index+1) + "</td>"
						+ "<td>" + vo.aptName + "</td>"
						+ "<td>" + vo.jibun + "</td>"
						+ "<td>" + vo.price + "</td>"
						+ "<td>" + vo.Date + "</td>"
						+ "</td><td id='lat_"+vo.lat+"'></td><td id='lng_"+vo.lng+"'></td></tr>";
						$("tbody").append(str);
					});//each
				geocode(data);
			}//function
			, "json"
	);//get
}

function geocode(jsonData) {
	let idx = 0;
	$.each(jsonData, function(index, vo) {
		let tmpLat = vo.lat;
		let tmpLng = vo.lng;
		$.get("https://maps.googleapis.com/maps/api/geocode/json"
				,{	key:'Google API Key'
					, address:vo.dong+"+"+vo.aptName+"+"+vo.jibun
				}
				, function(data, status) {
					$("#lat_"+index).text(tmpLat);
					$("#lng_"+index).text(tmpLng);
					addMarker(tmpLat, tmpLng, vo.aptName);
				}
				, "json"
		);//get
	});//each
}
</script>
</head>
<body>
</body>
</html>