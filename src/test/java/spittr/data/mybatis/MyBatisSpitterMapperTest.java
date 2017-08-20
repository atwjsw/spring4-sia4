package spittr.data.mybatis;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spittr.domain.Spitter;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by wenda on 8/20/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=MyBatisDataConfig.class)
//@ComponentScan(basePackages = "spittr.data.mybatis")
public class MyBatisSpitterMapperTest {

    @Autowired
    private MyBatisSpitterMapper myBatisSpitterMapper;

    @Test
    public void mapperShouldNotBeNull() {
        assertNotNull(myBatisSpitterMapper);
    }

    @Test
    public void testFindAllGmailSpitters() {
        List<Spitter> spitterList = myBatisSpitterMapper.findAllGmailSpitters();
        for (Spitter s: spitterList) {
            System.out.println(s);
        }
    }

    @Test
    public void testFindByUsername() {
       Spitter  spitter = myBatisSpitterMapper.findByUsername("habuma");
       System.out.println(spitter);

    }

}
