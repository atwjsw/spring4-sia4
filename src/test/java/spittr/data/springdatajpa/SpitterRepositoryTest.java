package spittr.data.springdatajpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import spittr.domain.Spitter;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by wenda on 8/20/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringDataConfig.class)
public class SpitterRepositoryTest {

    @Autowired
    private SpitterRepository spitterRepository;

    @Test
    public void testSpringDataJpaDefaultMethodFindAll() {
        assertNotNull(spitterRepository);
        List<Spitter> spitterList = spitterRepository.findAll();
        System.out.println(spitterList.size());
        for (Spitter s: spitterList) {
            System.out.println(s);
        }
    }

    @Test
    public void testSpringDataJpaDefaultMethodCount() {
        long count = spitterRepository.count();
        System.out.println(count);
    }

    @Test
    public void testfindByUsername() {
        Spitter s = spitterRepository.findByUsername("habuma");
        System.out.println(s);
    }

    @Test
    public void testreadByUsernameOrFullNameIgnoringCase () {
        List<Spitter> spitterList = spitterRepository.readByUsernameOrFullNameIgnoringCase("artnames", "craig walls");
        for (Spitter s: spitterList) {
            System.out.println(s);
        }
    }

    @Test
    public void testfindAllGmailSpitters () {
        List<Spitter> spitterList = spitterRepository.findAllGmailSpitters();
        for (Spitter s: spitterList) {
            System.out.println(s);
        }
    }

    @Test
    @Transactional
    public void testEliteSweep () {
        int count = spitterRepository.eliteSweep();
        System.out.println(count);

    }



}
