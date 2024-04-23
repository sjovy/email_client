package se.sjovy.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Email {
    private final UUID id;
    private final LocalDateTime dateTime;
    private String recipientEmail;
    private String subject;
    private String messageContent;

    //Constructor
    public Email(String recipientEmail, String subject, String messageContent) {
        this.id = UUID.randomUUID();
        this.dateTime = LocalDateTime.now();
        setRecipientEmail(recipientEmail);
        setSubject(subject);
        setMessageContent(messageContent);
    }

    //Setters
    public void setRecipientEmail(String recipientEmail) {
        if (recipientEmail == null || recipientEmail.isEmpty()) {
            throw new IllegalArgumentException("Recipient email cannot be null or empty");
        }
        this.recipientEmail = recipientEmail;
    }

    public void setSubject(String subject) {
        if (subject == null || subject.isEmpty()) {
            throw new IllegalArgumentException("Subject cannot be null or empty");
        }
        this.subject = subject;
    }

    public void setMessageContent(String messageContent) {
        if (messageContent == null || messageContent.isEmpty()) {
            throw new IllegalArgumentException("Message content cannot be null or empty");
        }
        this.messageContent = messageContent;
    }

    //Getters
    public UUID getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessageContent() {
        return messageContent;
    }

    //toString
    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", recipientEmail='" + recipientEmail + '\'' +
                ", subject='" + subject + '\'' +
                ", messageContent='" + messageContent + '\'' +
                '}';
    }
}