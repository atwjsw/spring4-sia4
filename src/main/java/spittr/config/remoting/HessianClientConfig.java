package spittr.config.remoting;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import spittr.service.SpittleService;

/**
 * Created by wenda on 8/24/2017.
 */
@Configuration
public class HessianClientConfig {

    @Bean
    public HessianProxyFactoryBean spittleService() {
        HessianProxyFactoryBean proxy = new HessianProxyFactoryBean();
        proxy.setServiceUrl("http://localhost:8080/spittle.service");
        proxy.setServiceInterface(SpittleService.class);
        return proxy;
    }
}
