package spittr.caching;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spittr.data.jdbc.JdbcConfig;
import spittr.data.jdbc.SpittleRepository;
import spittr.domain.Spitter;
import spittr.domain.Spittle;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by wenda on 8/21/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {CachingConfig.class, DataConfig.class})
//@ContextConfiguration(classes = {EhCacheConfig.class, JdbcConfig.class})
//@ContextConfiguration(classes = {RedisConfig.class, JdbcConfig.class})
@ContextConfiguration(classes = {CompositeConfig.class, JdbcConfig.class})
public class CachingTest {

    @Autowired
    SpittleRepository spittleRepository;

    @Test
    public void shouldNotNull() {
        System.out.println(spittleRepository);
    }

    @Test
    public void testNotCaching() {
        Spittle s = spittleRepository.findOne(2L);
        System.out.println(s);
    }

    @Test
    public void testCachable() {
        System.out.println("first query: ");
        Spittle s1 = spittleRepository.findOne(2L);
        System.out.println(s1);
        System.out.println("second query: ");
        Spittle s2 = spittleRepository.findOne(2L);
        System.out.println(s2);
        System.out.println("third query: ");
        Spittle s3 = spittleRepository.findOne(2L);
        System.out.println(s3);
    }

    @Test
    public void testCachePut() {
        Spitter spitter = spittleRepository.findOne(13).getSpitter();
        Spittle spittle = new Spittle(null, spitter, "Un Nuevo Spittle from Art", new Date());
        System.out.println(spittle);
        Spittle saved = spittleRepository.save(spittle);
        System.out.println(saved);
        Spittle s1 = spittleRepository.findOne(saved.getId());
        System.out.println(s1);
        Spittle s2 = spittleRepository.findOne(saved.getId());
        System.out.println(s2);
        Spittle s3 = spittleRepository.findOne(saved.getId());
        System.out.println(s3);
    }

    @Test
    public void testCachableUnless() {
        Spitter spitter = spittleRepository.findOne(13).getSpitter();
        Spittle spittle = new Spittle(null, spitter, "Un Nuevo Spittle from Art", new Date());
//        Spittle spittle = new Spittle(null, spitter, "Un Nuevo Spittle from Art NoCache...", new Date());
        System.out.println(spittle);
        Spittle saved = spittleRepository.save(spittle);
        System.out.println(saved);
        Spittle s1 = spittleRepository.findOne(saved.getId());
        System.out.println(s1);
        Spittle s2 = spittleRepository.findOne(saved.getId());
        System.out.println(s2);
        Spittle s3 = spittleRepository.findOne(saved.getId());
        System.out.println(s3);
    }

    @Test
    public void testCacheEvict() {

        Spittle s1 = spittleRepository.findOne(2L);
        System.out.println(s1);
        spittleRepository.delete(2L);
//        Spittle s2 = spittleRepository.findOne(2L);
//        System.out.println(s2);
//        Spittle s3 = spittleRepository.findOne(2L);
//        System.out.println(s3);

    }
}
