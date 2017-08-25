package spittr.messaging.jms.alerts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spittr.messaging.jms.domain.Spittle;

import java.io.IOException;
import java.util.Date;

/**
 * Created by wenda on 8/23/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="classpath:spittr/spittr.messaging/jms/jms-context.xml")
@ContextConfiguration(locations="classpath:jms-mdp-context.xml")
public class JMSMdpService {

    @Test
    public void testMDP() {

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
