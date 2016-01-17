package fr.julien.mailtest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * Created by julien on 17/01/16.
 */
@Service
public class MailService {

    private Logger logger = Logger.getLogger(MailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(Mail mail) {
        send(mail.getTo(), mail.getSubject(), mail.getText());
    }

    public void send(String to, String subject, String text) {
        MimeMessage mineMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mineMimeMessageHelper = new MimeMessageHelper(mineMessage, false, "utf-8");
            mineMimeMessageHelper.setTo(to);
            mineMimeMessageHelper.setSubject(subject);
            mineMimeMessageHelper.setText(text);
            mineMessage.setContent(text, "text/html");
            javaMailSender.send(mineMessage);
            logger.info("Mail sent to: " + to);
        } catch (Exception e) {
            logger.error(e);
        }
    }

}
