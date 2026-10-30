package org.una.programmingIII.WikiPets.Service;

import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmailServiceTest {

    private static final String FROM_ADDRESS = "sender@example.com";

    @Mock
    private JavaMailSender mailSender;

    private EmailService emailService;
    private MimeMessage mimeMessage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mimeMessage = new MimeMessage((Session) null);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        emailService = new EmailService(mailSender, FROM_ADDRESS);
    }

    @Test
    void sendSimpleEmail_UsesConfiguredSender() throws Exception {
        emailService.sendSimpleEmail("user@example.com", "Password recovery", "Use this token: abc");

        verify(mailSender).send(mimeMessage);
        assertArrayEquals(new InternetAddress[]{new InternetAddress(FROM_ADDRESS)}, mimeMessage.getFrom());
        assertArrayEquals(new InternetAddress[]{new InternetAddress("user@example.com")}, mimeMessage.getRecipients(Message.RecipientType.TO));
        assertEquals("Password recovery", mimeMessage.getSubject());
        assertTrue(mimeMessage.getContent().toString().contains("Use this token: abc"));
    }
}
