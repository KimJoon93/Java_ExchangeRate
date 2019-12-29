import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ExchangeRate {


    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) {
        
        ExchangeRate http = new ExchangeRate();
        try {
            http.sendGET(
                    "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey="인증키"&searchdate=20191227&data=AP01");
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

