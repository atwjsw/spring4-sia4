package spittr.web.rest.client;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import spittr.Spittle;
import spittr.web.SpittleForm;

import java.net.URI;
import java.util.Set;

/**
 * Created by wenda on 8/11/2017.
 */
public class HeadForHeadersTest {

    @Test
    public void testHeadForHeaderss() {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = rest.headForHeaders("http://localhost:8080/spittles/1");
        System.out.println(headers);
    }

    @Test
    public void testOptionsForAllow() {
        RestTemplate rest = new RestTemplate();
        Set<HttpMethod> methods = rest.optionsForAllow("http://localhost:8080/spittles/123");
        for (HttpMethod m: methods) {
            System.out.println(m);
        }
    }
}
