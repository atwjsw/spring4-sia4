package spittr.web.rest.client;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import spittr.Spitter;
import spittr.Spittle;

public class GetTest {

    @Test
    public void testGetForObject1() {
        RestTemplate rest = new RestTemplate();
        Long id = 1L;
        Spittle spittle = rest.getForObject("http://localhost:8080/spittles/"+ id, Spittle.class);
        System.out.println(spittle);
    }

    @Test
    public void testGetForObject2() {
        RestTemplate rest = new RestTemplate();
        Long id = 1L;
        Spittle spittle = rest.getForObject("http://localhost:8080/spittles/{id}", Spittle.class, id);
        System.out.println(spittle);
    }

    @Test
    public void testGetForEntity() {
        RestTemplate rest = new RestTemplate();
        Long id = 1L;
        ResponseEntity<Spittle> responseEntity = null;
        try {
            responseEntity = rest.getForEntity("http://localhost:8080/spittles/{id}", Spittle.class, id);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            System.out.println(responseEntity.getBody());
            System.out.println(responseEntity.getStatusCode());
            System.out.println(responseEntity.getHeaders());
        }


    }


}
