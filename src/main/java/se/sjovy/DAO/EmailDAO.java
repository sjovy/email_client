package se.sjovy.DAO;
import se.sjovy.model.Email;

import java.util.*;

public interface EmailDAO {
    void saveEmail(Email email);
    List<Email> getEmails();
    Optional<Email> findEmailById(UUID id);
    List<Email> findEmailsBySubject(String subject);
    List<Email> findEmailsByRecipient(String recipientEmail);
}