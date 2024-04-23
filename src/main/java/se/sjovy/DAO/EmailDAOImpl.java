package se.sjovy.DAO;

import se.sjovy.model.Email;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class EmailDAOImpl implements EmailDAO {
    private List<Email> emails;
    private static EmailDAOImpl instance;

    private EmailDAOImpl() {
        emails = new ArrayList<>();
    }

    public static EmailDAOImpl getInstance() {
        if (instance == null) {
            instance = new EmailDAOImpl();
        }
        return instance;
    }

    @Override
    public void saveEmail(Email email) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        emails.add(email);
    }

    @Override
    public List<Email> getEmails() {
        return emails;
    }

    @Override
    public Optional<Email> findEmailById(UUID id) {
        if (id == null) {
            return Optional.empty();
        }
        Objects.requireNonNull(id, "id cannot be null");
        return emails.stream()
                .filter(email -> email.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Email> findEmailsBySubject(String subject) {
        if (subject == null) {
            return new ArrayList<>();
        }
        Objects.requireNonNull(subject, "subject cannot be null");
        return emails.stream()
                .filter(email -> email.getSubject().equals(subject))
                .collect(Collectors.toList());
    }

    @Override
    public List<Email> findEmailsByRecipient(String recipientEmail) {
        if (recipientEmail == null) {
            return new ArrayList<>();
        }
        Objects.requireNonNull(recipientEmail, "recipientEmail cannot be null");
        return emails.stream()
                .filter(email -> email.getRecipientEmail().equals(recipientEmail))
                .collect(Collectors.toList());
    }
}