package fr.julien.mailtest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by julien on 17/01/16.
 */
@RestController
@RequestMapping("/mail")
public class RestService {

    private Logger logger = Logger.getLogger(RestService.class);

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "send", method = RequestMethod.POST)
    public String send(@RequestBody Mail mail) {
        logger.info("to: " + mail.getTo());
        logger.info("Su: " + mail.getSubject());
        logger.info("Te: " + mail.getText());
        mailService.send(mail);
        return "sent";
    }

}
