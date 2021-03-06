package com.suho.search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class LoadListAction{
	
	public void getLoad( HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String gugunNm = request.getParameter("gugunNm");
		String pageNo = request.getParameter("pageNo");
		
		if(pageNo == null){
			pageNo = "1";
		}
		
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6260000/BusanBicycleRoadService/getBicycleRoadInfo"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=tXyrwVeSLRn84CXxp6YRwCyYN1W3r%2FZWSOxncCCvENtvDAsBb%2Fi5amus91w%2Bp%2FYesxx9xraXWHJTIW5HN4jrzg%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("gugunNm","UTF-8") + "=" + URLEncoder.encode(gugunNm, "UTF-8")); /*구군명*/
        urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*JSON방식으로 호출 시 파라미터 resultType=json 입력*/
        URL url = new URL(urlBuilder.toString());
        
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        
        JSONParser parser = new JSONParser(); // String형을 Json 형태로 파싱하기 위한 객체 선언
        JSONObject jsonObj = null; // 파싱한 String 데이터를 담기 위한 JSONObject 객체 선언
        
        if(sb.indexOf("NODATA_ERROR") == -1){
        	 try {
             	
             	jsonObj = (JSONObject)parser.parse(sb.toString()); // String 데이터 파싱 후 JSONObject로 캐스팅
     			
     		} catch (ParseException e) {
     			System.out.println("파싱 오류 : " + e);
     			
     		}
        	 JSONObject getJson = (JSONObject)jsonObj.get("getBicycleRoadInfo"); // 오브젝트 안의 json 객체를 사용하기 위해 새로운 객체 선언
     		 
             response.setContentType("application/x-json; charset=UTF-8");
             response.getWriter().print(getJson);
        }
        else {
        	response.getWriter().print(0);
        }
	}

}
