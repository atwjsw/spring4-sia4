package spittr.mail;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
import spittr.mail.domain.Spittle;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wenda on 8/25/2017.
 */
@Service
public class SpittleMailServiceImpl implements SpittleMailService{
    @Autowired
    JavaMailSender mailSender;

    @Autowired
    VelocityEngine velocityEngine;

    @Autowired
    private SpringTemplateEngine thymeleaf;

    @Override
    public void sendSimpleSpittleEmail(String to, Spittle spittle) {
        SimpleMailMessage message = new SimpleMailMessage();		    //Construct message
        String spitterName = spittle.getSpitter().getFullName();
//        message.setFrom("noreply@spitter.com");
        message.setFrom("13928882387@163.com");	//Address email
        message.setTo(to);
        message.setSubject("New spittle from " + spitterName);
        message.setText(spitterName + " says: " + spittle.getText());   //Set message text
        mailSender.send(message);			                             //Send email
    }

    @Override
    public void sendSpittleEmailWithAttachment(String to, Spittle spittle) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);		//true indicates that this is to be a multipart message.
        String spitterName = spittle.getSpitter().getFullName();
//        helper.setFrom("noreply@spitter.com");
        helper.setFrom("13928882387@163.com");
        helper.setTo(to);
        helper.setSubject("New spittle from " + spitterName);
        helper.setText(spitterName + " says: " + spittle.getText());
        //using Spring’s FileSystemResource to load coupon.png from within the application’s classpath, pass that resource in as a parameter
//        FileSystemResource couponImage = new FileSystemResource("/collateral/coupon.jpg");
        ClassPathResource couponImage = new ClassPathResource("/collateral/coupon.jpg");
        helper.addAttachment("Coupon.png", couponImage);
        mailSender.send(message);
    }

    @Override
    public void sendRichSpittleEmail(String to, Spittle spittle) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("13928882387@163.com");
        helper.setTo(to);
        helper.setSubject("New spittle from " + spittle.getSpitter().getFullName());
        helper.setText("<html><body><img src='cid:spitterLogo'>" +
                "<h1>" + spittle.getSpitter().getFullName() + " says...</h1>" +
                "<i>" + spittle.getText() + "</i>" +
                "</body></html>", true);
        ClassPathResource image = new ClassPathResource("/collateral/spittr_logo_50.png");
        helper.addInline("spitterLogo", image);			//embed the logo image in the email message, <img src='cid:spitterLogo'>
        mailSender.send(message);
    }

    @Override
    public void sendRichSpittleEmailGeneratedByVelocity(String to, Spittle spittle) throws MessagingException, VelocityException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("13928882387@163.com");
        helper.setTo(to);
        helper.setSubject("New spittle from " + spittle.getSpitter().getFullName());
        Map<String, Object> model = new HashMap<>();
        model.put("spitterName", spittle.getSpitter().getFullName());
        model.put("spittleText", spittle.getText());
        String emailText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "mail/emailTemplate.vm", model);
        helper.setText(emailText, true);
        ClassPathResource image = new ClassPathResource("/collateral/spittr_logo_50.png");
        helper.addInline("spitterLogo", image);
        mailSender.send(message);
    }

    @Override
    public void sendRichSpittleEmailGeneratedByThymleaf(String to, Spittle spittle) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("13928882387@163.com");
        helper.setTo(to);
        helper.setSubject("New spittle from " + spittle.getSpitter().getFullName());
        Context ctx = new Context();
        ctx.setVariable("spitterName", spittle.getSpitter().getFullName());
        ctx.setVariable("spittleText", spittle.getText());
        String emailText = thymeleaf.process("emailTemplate.html", ctx);		//use Thymeleaf engine process the tempalte
        System.out.println(emailText);
        helper.setText(emailText, true);
        ClassPathResource image = new ClassPathResource("/collateral/spittr_logo_50.png");
        helper.addInline("spitterLogo", image);
        mailSender.send(message);
    }
}
