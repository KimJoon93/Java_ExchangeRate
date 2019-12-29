import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ExchangeRate {

    private final String USER_AGENT = "Mozilla/5.0";
    
    public static void main(String[] args) {
        
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
        String searchDate = dateformat.format(System.currentTimeMillis());
        
        Calendar cal = Calendar.getInstance();
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        
        if(dayOfWeek == 7){
            // Saturday
            searchDate = Integer.toString(Integer.parseInt(searchDate) -1);
        }else if(dayOfWeek == 1){
            // Sunday
            searchDate = Integer.toString(Integer.parseInt(searchDate) -2);
        }
        String AUTHKEY = "";
        ExchangeRate http = new ExchangeRate();
        String baseUrl = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?";
        String fullUrl = baseUrl + "authkey=" + AUTHKEY + "&searchdate=" + searchDate + "&data=AP01";
        
        try {
            http.sendGET(fullUrl);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void sendGET(String targetURL) throws Exception{
        
        URL url = new URL(targetURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode(); 
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream())); 
        String inputLine; 
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) { 
            response.append(inputLine); 
        } 
        in.close(); 
        
        System.out.println("HTTP 응답 코드 : " + responseCode); 
        System.out.println("HTTP body : " + response.toString());
    }
}