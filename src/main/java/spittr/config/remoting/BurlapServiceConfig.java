package spittr.config.remoting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.BurlapServiceExporter;
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
public class BurlapServiceConfig {
    @Autowired
    SpittleService spittleService;

    @Bean
    public BurlapServiceExporter burlapExportedSpittleService() {
        BurlapServiceExporter exporter = new BurlapServiceExporter();
        exporter.setService(spittleService);
        exporter.setServiceInterface(SpittleService.class);
        return exporter;
    }

    @Bean
    public HandlerMapping httpInvokerMapping() {
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        Properties mappings = new Properties();
        mappings.setProperty("/**", "defaultServletHttpRequestHandler");
        mappings.setProperty("spittle.service", "burlapExportedSpittleService");
        mapping.setMappings(mappings);
        return mapping;
    }
}
