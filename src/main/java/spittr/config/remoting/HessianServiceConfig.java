package spittr.config.remoting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import spittr.service.SpittleService;

import java.util.Properties;

/**
 * Created by wenda on 8/24/2017.
 */
@Configuration
@ComponentScan(basePackages = "spittr.service")
public class HessianServiceConfig {
    @Autowired
    SpittleService spittleService;

    @Bean
    public HessianServiceExporter hessianExportedSpittleService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(spittleService);
        exporter.setServiceInterface(SpittleService.class);
        return exporter;
    }

    @Bean
    public HandlerMapping httpInvokerMapping() {
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        Properties mappings = new Properties();
        mappings.setProperty("/**", "defaultServletHttpRequestHandler");
        mappings.setProperty("spittle.service", "hessianExportedSpittleService");
        mapping.setMappings(mappings);
        return mapping;
    }
}
