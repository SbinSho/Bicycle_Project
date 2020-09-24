<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/gugunNmHtml; charset=UTF-8">
<title>Insert title here</title>

<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

<script>
	function LoadList(page) {
		var gugunNm = $("#load option:selected").val(); // 현재 선택된 구명
		var pageNo = page;    // 리스트 페이지 번호
		var LoadListHTML = ""; // 자전거길 리스트 정보를 .html 담기위한 변수
		var pageNumHTML = "";  // 페이지 번호정보를 .html 담기위한 변수
		$.ajax({
			url:"LoadListAction.ld",
			dataType: "json",
			data: { gugunNm : gugunNm, pageNo : pageNo},
			success: function(data){
				if(data == 0){
					gugunNmHtml = "<h4>정보가 존재하지 않습니다.</h4>"
					$("#tt").empty();
				}
				else{
					$("#info").html("pageNo :" + data.pageNo + " totalCount : " + data.totalCount);
					for(var i = 0; i<data.item.length; i++){
						LoadListHTML +="<h4>"+(i+1)+"번째 결과</h4>";
						LoadListHTML += "<ul>";
						LoadListHTML += "<li>구군명: "+ data.item[i].gugunNm+"</li>";
						LoadListHTML += "<li>시작지점: "+ data.item[i].startSpot+"</li>";
						LoadListHTML += "<li>종료지점: "+ data.item[i].endSpot+"</li>";
						LoadListHTML += "<li>구간길이: "+ data.item[i].total+" km</li>";
						LoadListHTML += "<li>자전거전용 길이: "+ data.item[i].gugunOnlyBike+" km</li>";
						LoadListHTML += "<li>자전거보행자 겸용 길이 : "+ data.item[i].gugunWithWalk+" km</li>";
						LoadListHTML += "<li>자전거우선도로 길이: "+ data.item[i].gugunBikeRoad+" km</li>";
						LoadListHTML += "<li>갱신일 : "+ data.item[i].checkDate+"</li>";
						LoadListHTML += "</ul>";
						LoadListHTML = LoadListHTML.replace(/- km/gi, "정보가 존재 하지 않습니다."); // 정규식 표현으로 - km를 정보가 존재하지 않습니다로 변경.
					}
					for(var i = 1; i <= Math.ceil(data.totalCount/10); i++){
						pageNumHTML += "<a href='javascript:void(0);' onclick='LoadList("+i+");'>"+(i)+"</a>&nbsp;";
					}
				}
				
				$("#LoadList").html(LoadListHTML);
				$("#pageNum").html(pageNumHTML);
				$("html").scrollTop(0);
			},
			error : function(request, status, error) {
				alert("message:" + request.responseText + "\n" + "error:"+ error);
			}
		});
	}
	
</script>

</head>
<body>


	<h2>부산광역시 구별 자전거 도로 검색</h2>
	<label for="load">Choose a load:</label>

	<select name="load" id="load">
	  <option value="부산광역시 강서구">강서구</option>
	  <option value="부산광역시 금정구">금정구</option>
	  <option value="부산광역시 남구">남구</option>
	  <option value="부산광역시 동구">동구</option>
	  <option value="부산광역시 동래구">동래구</option>
	  <option value="부산광역시 부산진구">부산진구</option>
	  <option value="부산광역시 북구">북구</option>
	  <option value="부산광역시 사상구">사상구</option>
	  <option value="부산광역시 서구">서구</option>
	  <option value="부산광역시 수영구">수영구</option>
	  <option value="부산광역시 연제구">연제구</option>
	  <option value="부산광역시 영도구">영도구</option>
	  <option value="부산광역시 중구">중구</option>
	  <option value="부산광역시 해운대구">해운대구</option>
	</select>
	<input type="button" value="검색" id="serach" onclick="LoadList(1);">
	
	<div id ="info"></div>
	<div id="LoadList"></div>
	<div id="pageNum"></div>


</body>
</html>