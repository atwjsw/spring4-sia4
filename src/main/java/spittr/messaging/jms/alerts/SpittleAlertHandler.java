package spittr.messaging.jms.alerts;


import spittr.messaging.jms.domain.Spittle;

public class SpittleAlertHandler {

  public void handleSpittleAlert(Spittle spittle) {
    System.out.println(spittle.getMessage());
  }

}
