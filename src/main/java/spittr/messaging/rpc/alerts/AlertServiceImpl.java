package spittr.messaging.rpc.alerts;

import org.springframework.stereotype.Component;
import spittr.messaging.rpc.domain.Spittle;

/**
 * Created by wenda on 8/23/2017.
 */
//@Component("alertService")
public class AlertServiceImpl implements AlertService {
    public Spittle sendSpittleAlert(final Spittle spittle) {
        System.out.println("email send for " + spittle);
        return spittle;
    }
}
