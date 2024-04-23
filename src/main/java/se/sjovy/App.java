package se.sjovy;

import se.sjovy.DAO.*;
import se.sjovy.model.Email;
import se.sjovy.service.EmailService;

import javax.mail.MessagingException;

public class App {
    public static void main(String[] args) {
        Email email = new Email("recipient@example.com", "Hello", "This is a test email.");
        EmailDAOImpl emailDAO = EmailDAOImpl.getInstance();
        EmailDAOImpl emailDAO2 = EmailDAOImpl.getInstance();
        if (emailDAO == emailDAO2) {
            System.out.println("Singleton pattern is working. Both instances are the same.");
        } else {
            System.out.println("Singleton pattern is not working. Instances are different.");
        }

        emailDAO.saveEmail(email);
        System.out.println("Email saved successfully.");
        System.out.println(email.toString());

        /*email = new Email("optisizer@gmail.com", "Hello", "This is a test email.");
        EmailService emailService = new EmailService("thomas.sjovy@gmail.com", "");

        try {
            emailService.sendEmail(email);
            System.out.println("Email sent successfully");
        } catch (MessagingException e) {
            System.out.println("Failed to send email");
            e.printStackTrace();
        }*/

        //print number of elements in the list
        System.out.println(emailDAO.getEmails().size());
    }
}