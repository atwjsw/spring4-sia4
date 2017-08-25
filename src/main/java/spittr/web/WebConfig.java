package spittr.web;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import spittr.config.remoting.BurlapServiceConfig;
import spittr.config.remoting.HessianServiceConfig;
import spittr.config.remoting.HttpInvokerServiceConfig;

@Configuration
@EnableWebMvc
@ComponentScan({"spittr.web", "spittr.service"})
//@Import(HttpInvokerServiceConfig.class)
@Import(HessianServiceConfig.class)
//@Import(BurlapServiceConfig.class)
//@PropertySource(value="classpath:app.properties")
public class WebConfig extends WebMvcConfigurerAdapter {

//    1. Internal view resolver
//  @Bean
//  public ViewResolver viewResolver() {
//    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//    resolver.setPrefix("/WEB-INF/views/");
//    resolver.setSuffix(".jsp");
//    return resolver;
//  }

//    using jstl view
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
        return resolver;
    }

//    2. Tiles view resolver
//    @Bean
//    public TilesConfigurer tilesConfigurer() {
//        TilesConfigurer tiles = new TilesConfigurer();
//        tiles.setDefinitions(new String[]{
//                "/WEB-INF/layout/tiles.xml"            //Specify tile definition locations
//        });
//        tiles.setCheckRefresh(true);            //Enable refresh
//        return tiles;
//    }
//
//    @Bean
//    public ViewResolver viewResolver() {
//        return new TilesViewResolver();
//    }

//    3. Thymeleaf resolver
//    1)A ThymeleafViewResolver that resolves Thymeleaf template views from logical view names

//    @Bean
//    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
//        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//        viewResolver.setTemplateEngine(templateEngine);
//        return viewResolver;
//    }

//    2)A SpringTemplateEngine to process the templates and render the results
//    @Bean
//    public SpringTemplateEngine templateEngine(TemplateResolver templateResolver) {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver);
//        return templateEngine;
//    }
//    3)A TemplateResolver that loads Thymeleaf templates
//    @Bean
//    public TemplateResolver templateResolver() {
//        TemplateResolver templateResolver = new ServletContextTemplateResolver();
//        templateResolver.setPrefix("/WEB-INF/templates/");
//        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode("HTML5");
//        return templateResolver;
//    }

    //    3.contentNegotiationViewResolver
//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer.defaultContentType(MediaType.TEXT_HTML);
//    }
//
//    @Bean
//    public ViewResolver beanNameViewResolver() {
//        return new BeanNameViewResolver();
//    }
//
//    @Bean
//    public View spittles() {
//        return new MappingJackson2JsonView();
//    }

//    5. resourceBundle config
    //  @Bean
//  public MessageSource messageSource() {
//    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
////    messageSource.setBasename("classpath:messages"); //base name to resolve properties file names
//    messageSource.setBasename("messages");
//    return messageSource;
//  }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//    messageSource.setBasename("file:///etc/spittr/messages");
        messageSource.setBasename("classpath:messages");
        messageSource.setCacheSeconds(10);
        return messageSource;
    }



//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }

    @Bean
    public DefaultServletHttpRequestHandler defaultServletHttpRequestHandler() {
        return new DefaultServletHttpRequestHandler();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // TODO Auto-generated method stub
        super.addResourceHandlers(registry);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
