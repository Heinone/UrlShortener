package Shortener;

import org.apache.commons.validator.UrlValidator;


public class UrlChecker {


//  This class is to similarize urls and so to help check for duplicates. Every url is returned in form "http://enteredUrl"

    public String checkUrl(String url){
        UrlChecker u = new UrlChecker();
        url = u.similarize(url);
        url = u.checkWww(url);
        return url;


    }

    //  removes http:// or https:// from the beginning and / from the end. This so to help search for duplicates
    public String similarize(String url) {
        if (url.substring(0, 7).equals("http://")) {
            url = url.substring(7);
        }
        if (url.substring(0, 8).equals("https://")) {
            url = url.substring(8);
        }
        if (url.charAt(url.length() - 1) == '/') {
            url = url.substring(0, url.length() - 1);
        }
        return url;
    }

    //  Makes url validateable by inserting http:// in the beginning and then checks, if url is valid
    public String checkWww(String url){
        if("www.".equals(url.substring(0,4))) {
            String checker = "http://"+url;
            if(validate(checker)){
                url=checker;
                return url;
            }
        } else url = "http://"+url;
        if(validate(url))

            return url;

        return "X";
    }

    //  Checks if the url is valid
    public boolean validate(String url){
        UrlValidator urlValidator = new UrlValidator();
        if (urlValidator.isValid(url)) {
            return true;
        } else {
            return false;
        }

    }

}

