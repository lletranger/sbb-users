package org.tsys.sbb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tsys.sbb.util.EmailSender;

@Controller
public class IndexController {

    private EmailSender emailSender;

    @Autowired
    public void setEmailSender(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = {"/", "/index"})
    public String openIndex() {

        LOGGER.info("Session user now is {}",
                SecurityContextHolder.getContext().getAuthentication().getName());

        return "index";
    }

    @RequestMapping(value = "/send")
    public String sendEmail(@RequestParam("e-name") String ename, @RequestParam("e-msg") String emsg,
                            @RequestParam("e-sbj") String esbj, @RequestParam("e-adr") String eadr) {

        emailSender.send("konstelis@gmail.com", esbj,
                emsg.concat(". Author ")
                        .concat(ename).concat(". You can reply to address ")
                        .concat(eadr));
        LOGGER.info("Email was send from the index page");

        return "send";
    }

    @RequestMapping(value = "/nope")
    public String towerMode() {

        return "messages/somethingWentWrong";
    }
}
