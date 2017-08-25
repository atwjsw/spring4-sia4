package spittr.config.remoting;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.remoting.JmsInvokerProxyFactoryBean;
import spittr.service.SpittleService;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * Created by wenda on 8/24/2017.
 */
@Configuration
public class JMSClientConfig {

    @Bean
    public JmsInvokerProxyFactoryBean spittleService() {
        JmsInvokerProxyFactoryBean proxy = new JmsInvokerProxyFactoryBean();
        proxy.setConnectionFactory(connectionFactory());
        proxy.setQueue(spittleQueue());
        proxy.setServiceInterface(SpittleService.class);
        return proxy;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("tcp://localhost:61616");
        return connectionFactory;
    }


    @Bean
    public Queue spittleQueue() {
        return new ActiveMQQueue("spittle.alert.queue");
    }

    @Bean
    public Topic spittleTopic() {
        return new ActiveMQTopic("spittle.alert.topic");
    }

}
