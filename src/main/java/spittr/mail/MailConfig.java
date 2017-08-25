package spittr.mail;

import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.util.Properties;
import java.util.Set;

/**
 * Created by wenda on 8/25/2017.
 */
@Configuration
@ComponentScan(basePackages = "spittr.mail")
@PropertySource("classpath:mailserver.properties")
public class MailConfig {

    @Bean
    public JavaMailSender mailSender(Environment env) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty("mailserver.host"));
        mailSender.setPort(Integer.parseInt(env.getProperty("mailserver.port")));
        mailSender.setUsername(env.getProperty("mailserver.username"));
        mailSender.setPassword(env.getProperty("mailserver.password"));
        return mailSender;
    }

    @Bean
    public VelocityEngineFactoryBean velocityEngine() {			//VelocityEngineFactoryBean produces a VelocityEngine in the Spring application context.
        VelocityEngineFactoryBean velocityEngine = new VelocityEngineFactoryBean();
        Properties props = new Properties();
        props.setProperty("resource.loader", "class");
        props.setProperty("class.resource.loader.class", ClasspathResourceLoader.class.getName());		//load Velocity templates from the classpath
        velocityEngine.setVelocityProperties(props);
        return velocityEngine;
    }

    @Bean
    public ClassLoaderTemplateResolver emailTemplateResolver() {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("mail/"); //indicating that Thymeleaf templates is in the mail directory rooted at the classpath root
        resolver.setTemplateMode("HTML5");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setOrder(1);
        return resolver;
    }

    @Bean
    public ServletContextTemplateResolver webTemplateResolver() {
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setTemplateMode("HTML5");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setOrder(2);
        return resolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(Set<ITemplateResolver> resolvers) {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolvers(resolvers);
        return engine;
    }

}
