package spittr.config.remoting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.remoting.rmi.RmiServiceExporter;
import spittr.service.SpittleService;

/**
 * Created by wenda on 8/24/2017.
 */
@Configuration
@ComponentScan(basePackages = "spittr.service")
public class RMIServiceConfig {
    @Autowired
    SpittleService spittleService;

    @Bean
    public RmiServiceExporter rmiExporter() {
        RmiServiceExporter rmiExporter = new RmiServiceExporter();
        rmiExporter.setService(spittleService);
        rmiExporter.setServiceName("SpittleService");
        rmiExporter.setServiceInterface(SpittleService.class);
        return rmiExporter;
    }
}
