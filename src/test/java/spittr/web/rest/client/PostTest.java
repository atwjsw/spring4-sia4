package spittr.web.rest.client;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import spittr.Spittle;
import spittr.web.SpittleForm;

import java.net.URI;
import java.util.Date;

/**
 * Created by wenda on 8/11/2017.
 */
public class PostTest {

    @Test
    public void testPostForObject1() {
        RestTemplate rest = new RestTemplate();
        SpittleForm spittleForm = new SpittleForm();
        spittleForm.setMessage("This is a test spittle submitted from rest client");
        spittleForm.setLatitude(9.0);
        spittleForm.setLongitude(8.0);
        Spittle returnSpittle = rest.postForObject("http://localhost:8080/spittles/", spittleForm, Spittle.class);
        System.out.println(returnSpittle);
    }

    @Test
    public void testPostForEntitity() {
        RestTemplate rest = new RestTemplate();
        SpittleForm spittleForm = new SpittleForm();
        spittleForm.setMessage("This is a test spittle submitted from rest client");
        spittleForm.setLatitude(9.0);
        spittleForm.setLongitude(8.0);
        ResponseEntity<Spittle> responseEntity = rest.postForEntity("http://localhost:8080/spittles/", spittleForm, Spittle.class);
        System.out.println(responseEntity.getBody());
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getHeaders());
    }

    @Test
    public void testPostForLocation() {
        RestTemplate rest = new RestTemplate();
        SpittleForm spittleForm = new SpittleForm();
        spittleForm.setMessage("This is a test spittle submitted from rest client");
        spittleForm.setLatitude(9.0);
        spittleForm.setLongitude(8.0);
        URI locationUrl = rest.postForLocation("http://localhost:8080/spittles/", spittleForm, Spittle.class);
        System.out.println(locationUrl.toString());
    }
}
