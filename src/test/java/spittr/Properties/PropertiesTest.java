package spittr.Properties;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by wenda on 8/18/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=PropertiesConfig.class)
public class PropertiesTest {

    @Autowired
    Environment env;

    @Value("${disc.title}")
    String title;
    @Value("${disc.artist}")
    String artist;

    @Value("#{systemEnvironment.JAVA_HOME}")
    String javaHome;

//    @Value("#{systemProperties.testProp}")
//    String testProp;

//    @Value("#{test['disc.artist']}")
    String testSpELProp;

//    1) declare a property source and retrieve the properties via the Spring Environment.


    @Test
    public void testEnv() {
        System.out.println(env.getProperty("disc.title"));
        System.out.println(env.getProperty("disc.artist"));
        System.out.println(env.getProperty("disc.track"));
        System.out.println(env.getProperty("disc.track", "track"));
//        Integer i = env.getProperty("disc.number", Integer.class, 10);
        assertEquals("Sgt. Peppers Lonely Hearts Club Band",env.getProperty("disc.title"));
        assertEquals("Beatles",env.getProperty("disc.artist"));
        assertNull(env.getProperty("disc.track"));
        assertEquals("track",env.getProperty("disc.track", "track"));
        assertEquals(Integer.valueOf(10), env.getProperty("disc.number", Integer.class, 10));
    }

    //    2) RESOLVING PROPERTY PLACEHOLDERS from external properties files.
    @Test
    public void testExternalProperties() {
        System.out.println(title);
        System.out.println(artist);
        assertEquals("Sgt. Peppers Lonely Hearts Club Band",title);
        assertEquals("Beatles",artist);
    }

//   3） retrieves value from systemProperties or systemEnvironment:
    @Test
    public void testSystemEnvironment () {
        System.out.println(javaHome);
        assertEquals("C:\\Program Files\\Java\\jdk1.8.0_131", javaHome);
//        System.out.println(testProp);
    }

    @Test
    public void testSpEL() {
        System.out.println(testSpELProp);
    }


}
