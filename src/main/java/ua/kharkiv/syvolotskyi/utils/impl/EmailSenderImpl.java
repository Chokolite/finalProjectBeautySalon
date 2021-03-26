package ua.kharkiv.syvolotskyi.utils.impl;

import org.apache.log4j.Logger;
import ua.kharkiv.syvolotskyi.utils.EmailSender;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class EmailSenderImpl implements EmailSender {
    private static final Logger LOG = Logger.getLogger(EmailSenderImpl.class);

    private final List<String> emails;

    public EmailSenderImpl(List<String> emails) {
        this.emails = emails;
    }

    @Override
    public void notifyUsersAboutFeedback() {
        final String username = "adminName@gmail.com";
        final String password = "password";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            for (String email : emails) {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("adminName@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(email));
                message.setSubject("A testing mail header !!!");
                message.setText("Dear customer."
                        + "\n\n Please, leave a feedback!");

                Transport.send(message);
            }
        } catch (MessagingException e) {
            LOG.error(e);
        }
    }
}
