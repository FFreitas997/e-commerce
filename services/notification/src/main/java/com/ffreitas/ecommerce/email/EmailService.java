package com.ffreitas.ecommerce.email;

import com.ffreitas.ecommerce.kafka.order.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    @Value("${application.email.emailFrom}")
    private String emailFrom;
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendEmailPaymentSuccess(String emailTo, String customerName, BigDecimal amount, String orderReference) throws MessagingException {
        log.info("Sending Payment Success email to {} ...", customerName);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());

        helper.setFrom(emailFrom);

        String templateName = EmailTemplates.PAYMENT_CONFIRMATION.getTemplate();

        Map<String, Object> variables = Map.of(
                "customerName", customerName,
                "amount", amount,
                "orderReference", orderReference
        );

        Context context = new Context();
        context.setVariables(variables);
        helper.setSubject(EmailTemplates.PAYMENT_CONFIRMATION.getSubject());

        try {
            String html = templateEngine.process(templateName, context);
            helper.setText(html, true);
            helper.setTo(emailTo);
            mailSender.send(message);
            log.info("Email sent successfully to {}", emailTo);
        } catch (MessagingException e) {
            log.warn("WARNING: Cannot send email to {}", emailTo);
            throw e;
        }

    }

    @Async
    public void sendEmailOrderSuccess(String emailTo, String customerName, BigDecimal amount, String orderReference, List<Product> products) throws MessagingException {
        log.info("Sending Order Success email to {} ...", customerName);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());

        helper.setFrom(emailFrom);

        String templateName = EmailTemplates.ORDER_CONFIRMATION.getTemplate();

        Map<String, Object> variables = Map.of(
                "customerName", customerName,
                "amount", amount,
                "orderReference", orderReference,
                "products", products
        );

        Context context = new Context();
        context.setVariables(variables);
        helper.setSubject(EmailTemplates.ORDER_CONFIRMATION.getSubject());

        try {
            String html = templateEngine.process(templateName, context);
            helper.setText(html, true);
            helper.setTo(emailTo);
            mailSender.send(message);
            log.info("Email sent successfully to {}", emailTo);
        } catch (MessagingException e) {
            log.warn("WARNING: Cannot send email to {}", emailTo);
            throw e;
        }

    }
}
