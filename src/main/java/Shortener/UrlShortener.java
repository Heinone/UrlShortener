package Shortener;


import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.commons.collections4.map.PassiveExpiringMap;


public class UrlShortener {



    //  Map for checking, if url is already shortened
    private PassiveExpiringMap<String, String> urlMap;

    //  Map to get back the original url
    private PassiveExpiringMap<String, String> shortMap;

    private String originalUrl;
    private String shortenedUrl;
    private static final String letters = "abcdefghijklmnopqrstuvwxyz";
    private static final String numbers = "0123456789";
    private static final String lettersAndNumbers = letters + numbers;
    private static final String domain = "http://localhost/";
    private Random random;

    UrlChecker check = new UrlChecker();

    public UrlShortener() {
        urlMap = new PassiveExpiringMap<>(7, TimeUnit.DAYS);
        shortMap = new PassiveExpiringMap<>(7, TimeUnit.DAYS);
    }

//  Method for creating the short url. Checks if the url is already entered and returns the short version. If it doesn't excist, it creates a new one.
    public String encode (String original){
        String key = "";
        this.originalUrl = check.checkUrl(original);
        if(originalUrl=="X"){
            return "URL IS INVALID";
        }else if(urlMap.get(originalUrl)!=null) {
             key=urlMap.get(originalUrl);
             shortenedUrl = domain + key;
             return shortenedUrl;
        }else {
            key = generateKey();
            shortenedUrl = domain + key;
            urlMap.put(originalUrl, key);
            shortMap.put(shortenedUrl, originalUrl);
            return shortenedUrl;
        }
    }

//  Returns the url in original form, if found.
    public String decode (String shortenedUrl){
        String x= shortMap.get(shortenedUrl);
        if(x==null){
            return "Short url is invalid or expired";
        }
        return x;
    }

//  Generates the unique key in short url
    public String generateKey() {
        random=new Random();
        StringBuilder key=new StringBuilder();
        for (int i = 0; i < 17; i++) {
            int randIndex=random.nextInt(lettersAndNumbers.length());
            key.append(lettersAndNumbers.charAt(randIndex));
        }
        return key.toString();
    }
}
