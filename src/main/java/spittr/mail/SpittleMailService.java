package spittr.mail;

import org.apache.velocity.exception.VelocityException;
import spittr.mail.domain.Spittle;

import javax.mail.MessagingException;

/**
 * Created by wenda on 8/25/2017.
 */
public interface SpittleMailService {
    void sendSimpleSpittleEmail(String to, Spittle spittle);
    void sendSpittleEmailWithAttachment(String to, Spittle spittle) throws MessagingException;
    void sendRichSpittleEmail(String to, Spittle spittle) throws MessagingException;
    void sendRichSpittleEmailGeneratedByVelocity(String to, Spittle spittle) throws MessagingException, VelocityException;
    void sendRichSpittleEmailGeneratedByThymleaf(String to, Spittle spittle) throws MessagingException;
}
