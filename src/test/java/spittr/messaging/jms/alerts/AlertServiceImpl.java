package spittr.messaging.jms.alerts;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Service;
import spittr.messaging.jms.domain.Spittle;

/**
 * Created by wenda on 8/23/2017.
 */
@Service
public class AlertServiceImpl implements AlertService {

    @Autowired
    private JmsOperations jmsOperations;


    //    public AlertServiceImpl(JmsOperations jmsOperatons) {			//Inject JMS template
//        this.jmsOperations = jmsOperations;
//    }
//    1.1 send message
//    public void sendSpittleAlert(final Spittle spittle) {
//        System.out.println(jmsOperations);
////        jmsOperations.send("spittle.alert.queue", new MessageCreator() {			//Send message //Specify destination
//        jmsOperations.send(new MessageCreator() {                                 //default destination spittle.alert.queue" set in JMSTemplate bean
//                               public Message createMessage(Session session) throws JMSException {
//                                   return session.createObjectMessage(spittle);                    //Create message
//                               }
//                           }
//        );
//    }

    //    1.2 convert and send
    public void sendSpittleAlert(Spittle spittle) {
        jmsOperations.convertAndSend(spittle);
    }

    //    2.1 receive message
//    public Spittle receiveSpittleAlert() {
//        try {
////            ObjectMessage receivedMessage = (ObjectMessage) jmsOperations.receive("spittle.alert.queue");	//Receive message
//            ObjectMessage receivedMessage = (ObjectMessage) jmsOperations.receive(); //default destination spittle.alert.queue" set in JMSTemplate bean
//            return (Spittle) receivedMessage.getObject();                                //Get object
//        } catch (JMSException jmsException) {
//            throw JmsUtils.convertJmsAccessException(jmsException);                    //Throw converted exception
//        }
//    }

    //    2.2 receive and convert
    public Spittle receiveSpittleAlert() {
        return (Spittle) jmsOperations.receiveAndConvert();
    }
}
