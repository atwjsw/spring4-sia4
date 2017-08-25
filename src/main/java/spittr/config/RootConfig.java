package spittr.config;

import java.util.regex.Pattern;

import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import spittr.config.RootConfig.WebPackage;

@Configuration
@Import(DataConfig.class)
//@ImportResource("classpath:jms-context.xml")
//@ComponentScan(basePackages={"spittr"},
//        excludeFilters={
//                @Filter(type=FilterType.CUSTOM, value=WebPackage.class)
//        })
@PropertySource(value="classpath:app.properties")
public class RootConfig {
  public static class WebPackage extends RegexPatternTypeFilter {
    public WebPackage() {
      super(Pattern.compile("spittr\\.web"));
    }
  }
}


