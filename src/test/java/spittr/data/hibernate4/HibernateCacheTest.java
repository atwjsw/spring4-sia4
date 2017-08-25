package spittr.data.hibernate4;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import spittr.domain.Spittle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by wenda on 8/22/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateEhcacheDataConfig.class)
public class HibernateCacheTest {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    SpittleRepository spittleRepository;

    @Test
    @Transactional
    public void findOne() {
        Spittle thirteen = spittleRepository.findOne(13);
        System.out.println(thirteen);
        assertEquals(13, thirteen.getId().longValue());
        assertEquals("Bonjour from Art!", thirteen.getMessage());
        assertEquals(1332682500000L, thirteen.getPostedTime().getTime());
        assertEquals(4, thirteen.getSpitter().getId().longValue());
        assertEquals("artnames", thirteen.getSpitter().getUsername());
        assertEquals("password", thirteen.getSpitter().getPassword());
        assertEquals("Art Names", thirteen.getSpitter().getFullName());
        assertEquals("art@gmail.com", thirteen.getSpitter().getEmail());
        assertTrue(thirteen.getSpitter().isUpdateByEmail());
        Spittle thirteen2 = spittleRepository.findOne(13);
        System.out.println(thirteen2);
        Spittle thirteen3 = spittleRepository.findOne(13);
        System.out.println(thirteen3);
    }

    @Test
    @Transactional
    public void testSecondLevelCache() {
        System.out.println(sessionFactory);
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            System.out.println(sessionFactory.getCache().containsEntity(Spittle.class, 1L));
            Spittle spittle = (Spittle) session.load(Spittle.class, 1L);
            System.out.println("1 - " + spittle);
//            Spittle spittle2 = (Spittle) session.load(Spittle.class, 2L);
//            System.out.println("2 - " + spittle2);
            Spittle spittle3 = (Spittle) session.load(Spittle.class, 3L);
            System.out.println("3 - " + spittle3);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        System.out.println("----------------------------------------------------");

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            System.out.println(sessionFactory.getCache().containsEntity(Spittle.class, 1L));
            Spittle spittle = (Spittle) session.load(Spittle.class, 1L);
            System.out.println("1 - " + spittle);
            Spittle spittle2 = (Spittle) session.load(Spittle.class, 2L);
            System.out.println("2 - " + spittle2);
            Spittle spittle3 = (Spittle) session.load(Spittle.class, 3L);
            System.out.println("3 - " + spittle3);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            System.out.println(sessionFactory.getCache().containsEntity(Spittle.class, 1L));

            Spittle spittle2 = (Spittle) session.load(Spittle.class, 2L);
            System.out.println("2 - " + spittle2);
            Spittle spittle3 = (Spittle) session.load(Spittle.class, 3L);
            System.out.println("3 - " + spittle3);
            Spittle spittle = (Spittle) session.load(Spittle.class, 1L);
            System.out.println("1 - " + spittle);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        findOne();
    }



}

