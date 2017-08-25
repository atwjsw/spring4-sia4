package spittr.remoting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spittr.config.remoting.JMSServiceConfig;
import spittr.config.remoting.RMIServiceConfig;

import java.io.IOException;

/**
 * Created by wenda on 8/23/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes= RMIServiceConfig.class)
@ContextConfiguration(classes= JMSServiceConfig.class)
public class RemotingServiceRunner {

    @Test
    public void runService() {
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
