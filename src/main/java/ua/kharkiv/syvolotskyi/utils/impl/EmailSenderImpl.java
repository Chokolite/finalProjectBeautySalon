package ua.kharkiv.syvolotskyi.utils.impl;

import org.apache.log4j.Logger;
import ua.kharkiv.syvolotskyi.utils.EmailSender;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Takes parameters from a properties file(smtp.properties) and sends letters on mails from the list
 */
public class EmailSenderImpl implements EmailSender {
    private static final Logger LOG = Logger.getLogger(EmailSenderImpl.class);
    private final Authenticator authenticator;
    private final List<String> emails;
    private Properties properties;

    public EmailSenderImpl(List<String> emails) {
        initProperties();
        this.emails = emails;
        this.authenticator = initAuthenticator();
    }

    @Override
    public void notifyUsersAboutFeedback() {
        Session session = Session.getInstance(properties, authenticator);
        emails.forEach(email -> sendEmail(session, email));
    }

    private void sendEmail(Session session, String email) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("adminName@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("A testing mail header !!!");
            message.setText("Dear customer.\n\n Please, leave a feedback!");

            Transport.send(message);
        } catch (MessagingException e) {
            LOG.error(e);
        }
    }

    private Authenticator initAuthenticator() {
        String username = properties.getProperty("mail.smtp.username");
        String password = properties.getProperty("mail.smtp.password");
        PasswordAuthentication passwordAuthentication = new PasswordAuthentication(username, password);
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return passwordAuthentication;
            }
        };
    }

    private void initProperties() {
        properties = new Properties();
        try (FileReader fileReader = new FileReader(
                "C:\\Users\\ymomo\\IdeaProjects\\FinalProjectBeautySalon\\src\\main\\resources\\smtp.properties")){
            properties.load(fileReader);
        } catch (IOException e) {
            LOG.error(e);
        }
    }
}
