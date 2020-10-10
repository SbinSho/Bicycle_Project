<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/gugunNmHtml; charset=UTF-8">
<title>Insert title here</title>

<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

<script src="js/LoadList.js?ver=1"></script>

<style type="text/css">
	#pageNum {
		width: 100%;
		text-align: center;
		margin-top: 2em;
	}
	#LoadList {
		width: 100%;
		margin-top: 2em;
	}
	table{
		width: 800px;
		margin: auto;
		border-collapse: collapse;
	}
	td {
		border-bottom: 1px solid black;
	}
	tr:nth-child(even){
		background: ghostwhite;		
	}
	#info {
		width: 800px;
		text-align: right;
		margin-top : 2em;
		margin: auto;
	}
	#body {
		width: 100%;
		margin: auto;
		text-align: center;
	}
	#pageNum>a {
		text-decoration: none;
		color : black;
		font-size : 16px;
	}
	#pageNum>a:HOVER {
		color : red;
	}
	
</style>

</head>
<body>
<div id="body">
	<h2>부산광역시 구별 자전거 도로 검색</h2>
	<label for="load">구 선택:</label>

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

</div>

</body>
</html>