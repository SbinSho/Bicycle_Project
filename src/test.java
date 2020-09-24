import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6260000/BusanBicycleRoadService/getBicycleRoadInfo"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=tXyrwVeSLRn84CXxp6YRwCyYN1W3r%2FZWSOxncCCvENtvDAsBb%2Fi5amus91w%2Bp%2FYesxx9xraXWHJTIW5HN4jrzg%3D%3D"); /*Service Key*/
//        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + URLEncoder.encode("tXyrwVeSLRn84CXxp6YRwCyYN1W3r%2FZWSOxncCCvENtvDAsBb%2Fi5amus91w%2Bp%2FYesxx9xraXWHJTIW5HN4jrzg%3D%3D", "UTF-8")); /*공공데이터포털에서 발급받은 인증키*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("gugunNm","UTF-8") + "=" + URLEncoder.encode("부산광역시 해운대구", "UTF-8")); /*구군명*/
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
        
        try {
        	
        	jsonObj = (JSONObject)parser.parse(sb.toString()); // String 데이터 파싱 후 JSONObject로 캐스팅
			System.out.println(jsonObj);
		} catch (ParseException e) {
			System.out.println("파싱 오류 : " + e);
		}
        
        
        
        JSONObject getJson = (JSONObject)jsonObj.get("getBicycleRoadInfo"); // 오브젝트 안의 json 객체를 사용하기 위해 새로운 객체 선언
        JSONArray items = (JSONArray) getJson.get("item"); // API 응답값으로 받은 자전거 도로 정보를 조회하기 위한 JSONArray 선언
        
        String pageNo = getJson.get("pageNo").toString();
        String totalCount = getJson.get("totalCount").toString();
        String numOfRows = getJson.get("numOfRows").toString();
        
        
        System.out.println("pageNo : " + pageNo);
        System.out.println("totalCount : " + totalCount);
        System.out.println("numOfRows : " + numOfRows);
        
        for (Object arr : items) {
			
        	JSONObject test = (JSONObject) arr;
        	System.out.println("gugunNm : " + test.get("gugunNm"));
        	System.out.println("startSpot : " + test.get("startSpot"));
        	System.out.println("endSpot : " + test.get("endSpot"));
        	System.out.println("total : " + test.get("total"));
        	System.out.println("gugunOnlyBike : " + test.get("gugunOnlyBike"));
        	System.out.println("gugunWithWalk : " + test.get("gugunWithWalk"));
        	System.out.println("gugunBikeRoad : " + test.get("gugunBikeRoad"));
        	System.out.println("checkDate : " + test.get("checkDate"));
        	System.out.println("==============================");
		}
        
        
//        System.out.println(items.get(0));
        
        
        
        
        
        
    }
}