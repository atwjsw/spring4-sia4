package spittr.messaging.jms.alerts;

import spittr.messaging.jms.domain.Spittle;

/**
 * Created by wenda on 8/23/2017.
 */
public interface AlertService {
    void sendSpittleAlert(Spittle spittle);
    Spittle receiveSpittleAlert();
}
