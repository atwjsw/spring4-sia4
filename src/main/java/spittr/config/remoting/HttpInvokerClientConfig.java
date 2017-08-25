package spittr.config.remoting;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.CommonsHttpInvokerRequestExecutor;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerRequestExecutor;
import spittr.service.SpittleService;

/**
 * Created by wenda on 8/23/2017.
 */
@Configuration
public class HttpInvokerClientConfig {
    @Bean
    public HttpInvokerProxyFactoryBean spittleService() {
        HttpInvokerProxyFactoryBean proxy = new HttpInvokerProxyFactoryBean();
        proxy.setServiceUrl("http://localhost:8080/spittle.service");
        proxy.setServiceInterface(SpittleService.class);
        return proxy;
    }
}
