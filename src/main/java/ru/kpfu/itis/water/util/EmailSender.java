package ru.kpfu.itis.water.util;

import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
@Component
public class EmailSender {

    private JavaMailSender javaMailSender;
    private ExecutorService executorService;

    // TODO: 11.05.18
    public EmailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
        executorService = Executors.newCachedThreadPool();
    }

    @SneakyThrows
    public void sendEmailMessage(String destEmail, String subject, String text) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        mimeMessage.setContent(text, "text/html");
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(destEmail);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text, true);
        executorService.submit(() -> javaMailSender.send(mimeMessage));
    }
}