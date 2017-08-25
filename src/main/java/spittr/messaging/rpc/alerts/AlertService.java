package spittr.messaging.rpc.alerts;

import spittr.messaging.rpc.domain.Spittle;

/**
 * Created by wenda on 8/23/2017.
 */
public interface AlertService {
    Spittle sendSpittleAlert(Spittle spittle);
}
