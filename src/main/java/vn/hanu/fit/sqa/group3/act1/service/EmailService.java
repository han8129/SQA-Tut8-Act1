package vn.hanu.fit.sqa.group3.act1.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    @Async
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom("hello@example.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }
    }

    @Autowired public final JavaMailSender mailSender;
    private final static Logger LOGGER = LoggerFactory
        .getLogger(EmailService.class);

}
