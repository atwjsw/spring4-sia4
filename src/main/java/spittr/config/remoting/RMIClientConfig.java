package spittr.config.remoting;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import spittr.service.SpittleService;

/**
 * Created by wenda on 8/24/2017.
 */
@Configuration
public class RMIClientConfig {
    @Bean
    public RmiProxyFactoryBean spittleService() {
        RmiProxyFactoryBean rmiProxy = new RmiProxyFactoryBean();
        rmiProxy.setServiceUrl("rmi://localhost/SpittleService");
        rmiProxy.setServiceInterface(SpittleService.class);
        return rmiProxy;
    }

}
