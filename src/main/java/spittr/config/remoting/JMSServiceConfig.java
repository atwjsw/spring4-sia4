package spittr.config.remoting;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.jms.remoting.JmsInvokerServiceExporter;
import org.springframework.remoting.rmi.RmiServiceExporter;
import spittr.service.SpittleService;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * Created by wenda on 8/24/2017.
 */
@Configuration
@ComponentScan(basePackages = "spittr.service")
public class JMSServiceConfig {
    @Autowired
    SpittleService spittleService;

    @Bean
    public JmsInvokerServiceExporter exporter() {
        JmsInvokerServiceExporter exporter = new JmsInvokerServiceExporter();
        exporter.setService(spittleService);
        exporter.setServiceInterface(SpittleService.class);
        return exporter;
    }

    @Bean
    public MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(exporter());
    }

    @Bean
    public DefaultMessageListenerContainer jmsContainer() {
        DefaultMessageListenerContainer jmsContainer = new DefaultMessageListenerContainer();
        jmsContainer.setConnectionFactory(connectionFactory());
        jmsContainer.setDestination(spittleQueue());
        jmsContainer.setMessageListener(messageListener());
        return jmsContainer;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("tcp://localhost:61616");
        return connectionFactory;
    }


    @Bean
    public Queue spittleQueue() {
        return new org.apache.activemq.command.ActiveMQQueue("spittle.alert.queue");
    }

    @Bean
    public Topic spittleTopic() {
        return new org.apache.activemq.command.ActiveMQTopic("spittle.alert.topic");
    }
}
