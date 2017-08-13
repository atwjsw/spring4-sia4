package spittr.web.rest.client;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import spittr.Spittle;
import spittr.web.SpittleForm;

public class ExchangeTest {

    @Test
    public void testExchange1() {
        RestTemplate rest = new RestTemplate();
        Long id = 1L;
        ResponseEntity<Spittle> responseEntity = rest.exchange("http://localhost:8080/spittles/{id}",
                HttpMethod.GET, null, Spittle.class, id);
        System.out.println(responseEntity.getBody());
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getHeaders());
    }

    //specify application/json as the only value in the Accept header.
    @Test
    public void testExchangeWithRequestHeader() {
        RestTemplate rest = new RestTemplate();
        Long id = 1L;
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Accept", "application/json");
        HttpEntity requestEntity = new HttpEntity(headers);

        ResponseEntity<Spittle> responseEntity = rest.exchange("http://localhost:8080/spittles/{id}",
                HttpMethod.GET, requestEntity, Spittle.class, id);
        System.out.println(responseEntity.getBody());
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getHeaders());
    }

}
