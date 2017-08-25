package spittr.remoting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spittr.Spittle;
import spittr.config.remoting.*;
import spittr.service.SpittleService;

/**
 * Created by wenda on 8/23/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes= HttpInvokerClientConfig.class)
//@ContextConfiguration(classes= RMIClientConfig.class)
//@ContextConfiguration(classes= JMSClientConfig.class)
//@ContextConfiguration(classes= HessianClientConfig.class)
@ContextConfiguration(classes= BurlapClientConfig.class)
public class RemotingClientTest {

    @Autowired
    SpittleService spittleService;

    @Test
    public void testRPC() {
        System.out.println(spittleService);
        Spittle spittle = spittleService.getSpittleById(1L);
        System.out.println(spittle);
    }


}
