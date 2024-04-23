package se.sjovy.DAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.sjovy.model.Email;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EmailDAOTest {
    private EmailDAOImpl emailDAO;
    private Email testEmail;

    @BeforeEach
    void setUp() {
        emailDAO = EmailDAOImpl.getInstance();
        testEmail = new Email("test@example.com", "Test", "This is a test email.");
        emailDAO.saveEmail(testEmail);
    }

    @Test
    void singletonPatternShouldWork() {
        EmailDAOImpl instance1 = EmailDAOImpl.getInstance();
        EmailDAOImpl instance2 = EmailDAOImpl.getInstance();
        assertSame(instance1, instance2, "Instances are not the same. Singleton pattern is not working.");
    }

    @Test
    void shouldSaveEmailCorrectly() {
        Email email = new Email("test@example.com", "Test", "This is a test email.");
        emailDAO.saveEmail(email);
        assertTrue(emailDAO.getEmails().contains(email), "Email was not saved correctly.");
    }

    @Test
    void shouldReturnEmailWhenIdExists() {
        Optional<Email> foundEmail = emailDAO.findEmailById(testEmail.getId());
        assertTrue(foundEmail.isPresent(), "Email was not found by id.");
        assertEquals(testEmail, foundEmail.get(), "Found email does not match the saved email.");
    }

    @Test
    void shouldNotReturnEmailWhenIdDoesNotExist() {
        Optional<Email> foundEmail = emailDAO.findEmailById(UUID.randomUUID());
        assertFalse(foundEmail.isPresent(), "An email was found with a random id.");
    }

    @Test
    void shouldReturnEmailsWhenSubjectExists() {
        List<Email> foundEmails = emailDAO.findEmailsBySubject(testEmail.getSubject());
        assertFalse(foundEmails.isEmpty(), "No emails were found by subject.");
        assertTrue(foundEmails.contains(testEmail), "Saved email was not found by subject.");
    }

    @Test
    void shouldNotReturnEmailsWhenSubjectDoesNotExist() {
        List<Email> foundEmails = emailDAO.findEmailsBySubject("Nonexistent Subject");
        assertTrue(foundEmails.isEmpty(), "Emails were found with a nonexistent subject.");
    }

    @Test
    void shouldReturnEmailsWhenRecipientExists() {
        List<Email> foundEmails = emailDAO.findEmailsByRecipient(testEmail.getRecipientEmail());
        assertFalse(foundEmails.isEmpty(), "No emails were found for the recipient.");
        assertTrue(foundEmails.contains(testEmail), "Saved email was not found for the recipient.");
    }

    @Test
    void shouldNotReturnEmailsWhenRecipientDoesNotExist() {
        List<Email> foundEmails = emailDAO.findEmailsByRecipient("nonexistent.recipient@example.com");
        assertTrue(foundEmails.isEmpty(), "Emails were found for a nonexistent recipient.");
    }

    @Test
    void shouldThrowExceptionWhenSavingNullEmail() {
        assertThrows(IllegalArgumentException.class, () -> emailDAO.saveEmail(null));
    }

    @Test
    void shouldReturnEmptyWhenSearchingWithNullId() {
        Optional<Email> foundEmail = emailDAO.findEmailById(null);
        assertFalse(foundEmail.isPresent());
    }

    @Test
    void shouldReturnEmptyWhenSearchingWithEmptySubject() {
        List<Email> foundEmails = emailDAO.findEmailsBySubject("");
        assertTrue(foundEmails.isEmpty());
    }

    @Test
    void shouldReturnEmptyWhenSearchingWithNullSubject() {
        List<Email> foundEmails = emailDAO.findEmailsBySubject(null);
        assertTrue(foundEmails.isEmpty());
    }

    @Test
    void shouldReturnEmptyWhenSearchingWithEmptyRecipient() {
        List<Email> foundEmails = emailDAO.findEmailsByRecipient("");
        assertTrue(foundEmails.isEmpty());
    }

    @Test
    void shouldReturnEmptyWhenSearchingWithNullRecipient() {
        List<Email> foundEmails = emailDAO.findEmailsByRecipient(null);
        assertTrue(foundEmails.isEmpty());
    }
}