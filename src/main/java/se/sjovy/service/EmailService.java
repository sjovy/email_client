package se.sjovy.service;

import se.sjovy.model.Email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {
    private String username;
    private String password;

    public EmailService(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void sendEmail(Email email) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");  // GMAIL here
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = prepareMessage(session, email);

        Transport.send(message);
    }

    private Message prepareMessage(Session session, Email email) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getRecipientEmail()));
        message.setSubject(email.getSubject());
        message.setText(email.getMessageContent());
        return message;
    }
}