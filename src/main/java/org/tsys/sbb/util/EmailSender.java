package org.tsys.sbb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {

    private static final Logger logger = LoggerFactory.getLogger(EmailSender.class);

    public void send(String emailTo, String msgSubject, String msgText) {

        String fromEmail = "sbbtestsys@gmail.com";
        String fromEmailPassword = "hmq-yqb-q5B-m8u";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, fromEmailPassword);
            }
        });


        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));

            message.setSubject(msgSubject);
            message.setText(msgText);

            Transport.send(message);

            logger.info("Email send successfully");

        } catch (MessagingException mex) {
            logger.error(mex.toString());
        }
    }
}