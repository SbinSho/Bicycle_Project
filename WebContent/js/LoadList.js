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
				if(data == 0){ // 검색 결과가 존재하지 않으면 0을 리턴함
					LoadListHTML = "<h4>정보가 존재하지 않습니다.</h4>"
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
						LoadListHTML += "<td>"+ data.item[i].startSpot+"</td>"; // 자전거 도로 시작지점
						LoadListHTML += "<td>"+ data.item[i].endSpot+"</td>"; // 자전거 도로 종료지점
						LoadListHTML += "<td>"+ data.item[i].checkDate+"</td>"; // 데이터 갱신일자
						LoadListHTML += "</tr>";
						LoadListHTML = LoadListHTML.replace(/- km/gi, "정보가 존재 하지 않습니다."); // 정규식 표현으로 - km를 정보가 존재하지 않습니다로 변경.
					}
					LoadListHTML += "</table>";
					for(var i = 1; i <= Math.ceil(data.totalCount/10); i++){
						pageNumHTML += "<a href='javascript:void(0);' onclick='LoadList("+i+");'>"+(i)+"</a>&nbsp;";
					}
				}
				
				$("#LoadList").html(LoadListHTML); // 불러온 도로 리스트를 LoadList div태그에 추가
				$("#pageNum").html(pageNumHTML); // 불러온 페이저 번호를 pageNum div 태그에 추가
				$("html").scrollTop(0); // 데이터를 불러온 후 무조건 페이지 제일 상단으로 이동
			},
			error : function(request, status, error) {
				alert("message:" + request.responseText + "\n" + "error:"+ error);
			}
		});
	}