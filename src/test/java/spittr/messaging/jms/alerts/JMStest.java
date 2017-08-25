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
@ContextConfiguration(locations="classpath:jms-context.xml")
public class JMStest {

    @Autowired
    AlertService alertService;

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    JmsOperations jmsOperations;

//    @Autowired
//    SpittleAlertHandler spittleHandler;

    @Test
    public void shouldNotNull() {
//        System.out.println(spittleHandler);
        System.out.println(jmsOperations);
        System.out.println(alertService);
        System.out.println(jmsTemplate);
    }

    @Test
    public void testSendMessage() {
        Spittle spittle = new Spittle(1L, null, "Hello", new Date());
        alertService.sendSpittleAlert(spittle);
    }

    @Test
    public void testSendRpcMessage() {
        spittr.messaging.rpc.domain.Spittle spittle =
                new spittr.messaging.rpc.domain.Spittle(1L, null, "Hello", new Date());
        jmsOperations.convertAndSend(spittle);
    }


    @Test
    public void testSendManyMessages() {
        for(int i=0; i< 10; i++) {
            Spittle spittle = new Spittle((long)i, null, "Hello" + i, new Date());
            alertService.sendSpittleAlert(spittle);
        }
    }

    @Test
    public void testReceiveMessage() {
        Spittle spittle = alertService.receiveSpittleAlert();
        System.out.println(spittle);
    }

    @Test
    public void testMDP() {

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
