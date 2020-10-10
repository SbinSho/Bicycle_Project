/**
 * 
 */

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
					LoadListHTML = "<h4>정보가 존재하지 않습니다.</h4>"
//					$("#tt").empty();
				}
				else{
					$("#info").html("현재 페이지 번호 : " + data.pageNo + " 총 검색결과 : " + data.totalCount);
					LoadListHTML += "<table>";
					LoadListHTML +="<tr>";
					LoadListHTML += "<th>시작지점</th>";
					LoadListHTML += "<th>종료지점</th>";
					LoadListHTML += "<th>갱신일</th>";
					LoadListHTML += "</tr>";
					for(var i = 0; i<data.item.length; i++){
						LoadListHTML +="<tr>";
						LoadListHTML += "<td>"+ data.item[i].startSpot+"</td>";
						LoadListHTML += "<td>"+ data.item[i].endSpot+"</td>";
						LoadListHTML += "<td>"+ data.item[i].checkDate+"</td>";
						LoadListHTML += "</tr>";
						LoadListHTML = LoadListHTML.replace(/- km/gi, "정보가 존재 하지 않습니다."); // 정규식 표현으로 - km를 정보가 존재하지 않습니다로 변경.
//						LoadListHTML += "<div class='result'>";
//						LoadListHTML +="<h4>"+(i+1)+"번째 결과</h4>";
//						LoadListHTML += "<ul>";
//						LoadListHTML += "<li>구군명: "+ data.item[i].gugunNm+"</li>";
//						LoadListHTML += "<li>시작지점: "+ data.item[i].startSpot+"</li>";
//						LoadListHTML += "<li>종료지점: "+ data.item[i].endSpot+"</li>";
//						LoadListHTML += "<li>구간길이: "+ data.item[i].total+" km</li>";
//						LoadListHTML += "<li>자전거전용 길이: "+ data.item[i].gugunOnlyBike+" km</li>";
//						LoadListHTML += "<li>자전거보행자 겸용 길이 : "+ data.item[i].gugunWithWalk+" km</li>";
//						LoadListHTML += "<li>자전거우선도로 길이: "+ data.item[i].gugunBikeRoad+" km</li>";
//						LoadListHTML += "<li>갱신일 : "+ data.item[i].checkDate+"</li>";
//						LoadListHTML += "</ul>";
//						LoadListHTML += "</div>";
//						LoadListHTML = LoadListHTML.replace(/- km/gi, "정보가 존재 하지 않습니다."); // 정규식 표현으로 - km를 정보가 존재하지 않습니다로 변경.
					}
					LoadListHTML += "</table>";
					for(var i = 1; i <= Math.ceil(data.totalCount/10); i++){
						pageNumHTML += "<a href='javascript:void(0);' onclick='LoadList("+i+");'>"+(i)+"</a>&nbsp;";
					}
				}
				
				$("#LoadList").html(LoadListHTML);
				$("#pageNum").html(pageNumHTML);
				$("html").scrollTop(0); // 데이터를 불러온 후 무조건 페이지 제일 상단으로 이동
			},
			error : function(request, status, error) {
				alert("message:" + request.responseText + "\n" + "error:"+ error);
			}
		});
	}