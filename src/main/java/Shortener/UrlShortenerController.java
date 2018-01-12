package Shortener;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlShortenerController {
    UrlShortener urlshrtr = new UrlShortener();

    @RequestMapping("/shorten")
    public String urlShortener(@RequestParam(value="url") String url){
        String x = urlshrtr.encode(url);
        return x;
    }

    @RequestMapping("/expand")
    public String expander(@RequestParam(value="url") String url){
        String y = urlshrtr.decode(url);
        return y;
    }
}
