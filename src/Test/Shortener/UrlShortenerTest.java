package Shortener;

import org.apache.commons.collections4.map.PassiveExpiringMap;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class UrlShortenerTest {

    private PassiveExpiringMap<String, String> shortMap;

    @Test
    public void testExpiry() throws InterruptedException {
        shortMap = new PassiveExpiringMap<>(5, TimeUnit.SECONDS);
        shortMap.put("Test", "Answer");
        shortMap.put("Test2","FAIL");
        String x = shortMap.get("Test");
        assertEquals("Answer", x);
        TimeUnit.SECONDS.sleep(6);
        String y = shortMap.get("Test2");
        assertNotEquals("FAIL", y);
        assertEquals(null, y);
    }

}