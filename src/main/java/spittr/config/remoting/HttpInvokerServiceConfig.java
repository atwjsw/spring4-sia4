package spittr.config.remoting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import spittr.Spittle;
import spittr.service.SpittleService;
import spittr.service.SpittleServiceImpl;

import java.util.List;
import java.util.Properties;

/**
 * Created by wenda on 8/23/2017.
 */
@Configuration
@ComponentScan(basePackages = "spittr.service")
public class HttpInvokerServiceConfig {

    @Autowired
    SpittleService spittleService;

    @Bean
    public HttpInvokerServiceExporter httpExportedSpittleService() {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(spittleService);
        exporter.setServiceInterface(SpittleService.class);
        return exporter;
    }

    @Bean
    public HandlerMapping httpInvokerMapping() {
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        Properties mappings = new Properties();
        mappings.setProperty("/spittle.service", "httpExportedSpittleService");
        mappings.setProperty("/**", "defaultServletHttpRequestHandler");
        mapping.setMappings(mappings);
        return mapping;
    }

}
