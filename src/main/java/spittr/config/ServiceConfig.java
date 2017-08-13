package spittr.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wenda on 8/13/2017.
 */
@Configuration
@ComponentScan(basePackages = "spittr.service")
public class ServiceConfig {
}
