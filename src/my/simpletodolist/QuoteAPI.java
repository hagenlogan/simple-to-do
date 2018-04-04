package my.simpletodolist;

/**
 *
 * @author logan
 */
import java.io.*;
import java.net.*;

public class QuoteAPI {

    private String quote;

    public QuoteAPI() {

    }
    
    public String getQuote(){
        return this.quote;
    }  

    public void quoteRequest() throws Exception {

        URL url = new URL("https://talaikis.com/api/quotes/random/");
        URLConnection conn = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        
        String inputLine;
        String quote = "";

        while ((inputLine = in.readLine()) != null) {
            
            int firstIndex = 9; // Every result begins with {"quote":
            int secondIndex = 0;
            int thirdIndex = 0;

            for (int i = firstIndex; i < inputLine.length(); i++) {
                
                // Parses inputLine for sequence ,"a of chars
                if ((inputLine.charAt(i) == 0x2c) && (inputLine.charAt(i + 1) == 0x22) && (inputLine.charAt(i + 2) == 0x61)) {
                    secondIndex = i;
                }
                
                // Parses inputLine for sequence ":" of chars
                if ((inputLine.charAt(i) == 0x22) && (inputLine.charAt(i + 1) == 0x3a) && (inputLine.charAt(i + 2) == 0x22)) {
                    thirdIndex = i - 5;
                }   
            }
            this.quote = "" + inputLine.substring(firstIndex, secondIndex) + " - " + inputLine.substring((secondIndex + 11), (thirdIndex - 1));
        }
        in.close();
    }
}
