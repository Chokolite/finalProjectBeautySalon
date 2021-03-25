package ua.kharkiv.syvolotskyi.controller.common;

import org.apache.log4j.Logger;
import ua.kharkiv.syvolotskyi.entity.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.Properties;

public class SendMail {
    private User user;
    private LocalDateTime finishedServiceDateTime;
    private static final Logger LOGGER = Logger.getLogger(SendMail.class);


    public SendMail(User user, LocalDateTime finishedServiceDateTime) {
        this.user = user;
        this.finishedServiceDateTime = finishedServiceDateTime;
    }

    public static void main(String[] args) {
        User testUser = new User();
        testUser.setName("TestUser");
        testUser.setEmail("test@gmail.com");
        SendMail sendMail = new SendMail(testUser, LocalDateTime.now());
        sendMail.shelude();
    }

    public void shelude() {
        LocalDateTime taskDay = finishedServiceDateTime.plusMinutes(2);
        new Thread(() -> {
            while (true) {
                if (LocalDateTime.now().getMinute() == (taskDay.getMinute())) {
                    sendMail(user.getEmail(), user.getName());
                    break;
                } else {
                    try {
                        Thread.sleep(60 * 1 * 1000);
                    } catch (Exception e) {
                        System.out.println("sendmail shelude exception:" + e);
                    }
                }
            }
        }).start();
    }

    private void sendMail(String email, String userName) {
        final String username = "email@gmail.com";
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

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("eduard.sivolotskiy@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("A testing mail header !!!");
            message.setText("Dear " + userName + ","
                    + "\n\n Please, leave a feedback!");

            Transport.send(message);

            LOGGER.debug(LocalDateTime.now() + " Mail send");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
