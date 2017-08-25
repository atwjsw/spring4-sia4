package spittr.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import spittr.messaging.jms.alerts.SpittleAlertHandler;

@Controller
//@RequestMapping("/")
@RequestMapping({"/", "/homepage"})
public class HomeController {



  @Autowired
  Environment env;

  @Value("${disc.title}")
  String title;
  @Value("${disc.artist}")
  String artist;

  @Value("#{systemEnvironment.JAVA_HOME}")
  String javaHome;

//  @Value("#{systemProperties.testProp}")
//  String testProp;

  @RequestMapping(method = GET)
//  public String home(Model model) {
//    return "home";
//  }
  public String home() {
    testProperties();
    return "home";
  }

  private void testProperties() {
    System.out.println(env.getProperty("disc.title"));
    System.out.println(env.getProperty("disc.artist"));
    System.out.println(title);
    System.out.println(artist);
    System.out.println(javaHome);
  }

}
