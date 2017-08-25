package spittr.messaging.rpc.alerts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spittr.messaging.rpc.domain.Spittle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by wenda on 8/23/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:jms-rpc-client.xml")
public class AlertServiceClient {

    @Autowired
    AlertService alertService;

    @Test
    public void shouldNotNull() {
        System.out.println(alertService);
    }

    @Test
    public void testRPCoverJMS() {
        Spittle spittle = new Spittle(1L, null, "Hello", new Date());
        Spittle returnSpittle = alertService.sendSpittleAlert(spittle);
        System.out.println(returnSpittle);
    }

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:jms-rpc-client.xml");
        AlertService alertService = context.getBean(AlertService.class);
        for (;;) {
            System.out.print("Please input spittle message: ");
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();
            Spittle spittle = new Spittle(1L, null, message, new Date());
            Spittle returnSpittle = alertService.sendSpittleAlert(spittle);
            System.out.println(returnSpittle);
        }
    }
}
