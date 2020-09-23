<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

<script>

	$(function(){
		$("#serach").click(function(){
			var param = $("#load option:selected").val();
			var html = "";
			
			$.ajax({
				url:"LoadListAction.ld",
				dataType: "json",
				data: { param : param},
				success: function(data){
					if(data == 0){
						html += "<h4>정보가 존재하지 않습니다.</h4>"
					}
					else{
						$.each(data, function(index, item){
							html +="<h4>"+(index+1)+"번째 결과</h4>";
							html += "<ul>";
							html += "<li>구군명: "+item.gugunNm+"</li>";
							html += "<li>시작지점: "+item.startSpot+"</li>";
							html += "<li>종료지점: "+item.endSpot+"</li>";
							html += "<li>구간길이: "+item.total+" km</li>";
							html += "<li>자전거전용 길이: "+item.gugunOnlyBike+" km</li>";
							html += "<li>자전거보행자 겸용 길이 : "+item.gugunWithWalk+" km</li>";
							html += "<li>자전거우선도로 길이: "+item.gugunBikeRoad+" km</li>";
							html += "<li>"+item.checkDate+"</li>";
							html += "</ul>";
						});
					}
					$("#test").html(html);
				},
				error : function(request, status, error) {
					alert("message:" + request.responseText + "\n" + "error:"
							+ error);
				}
			});
		});	
	});
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
	<input type="button" value="검색" id="serach">

	<div id="test">
	
	
	</div>


</body>
</html>