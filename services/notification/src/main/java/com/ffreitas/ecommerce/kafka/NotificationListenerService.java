package com.ffreitas.ecommerce.kafka;

import com.ffreitas.ecommerce.email.EmailService;
import com.ffreitas.ecommerce.kafka.order.OrderConfirmation;
import com.ffreitas.ecommerce.kafka.payment.PaymentConfirmation;
import com.ffreitas.ecommerce.notification.Notification;
import com.ffreitas.ecommerce.notification.NotificationRepository;
import com.ffreitas.ecommerce.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationListenerService {

    private final NotificationRepository repository;
    private final EmailService emailService;

    @KafkaListener(topics = "${application.kafka.payment-topic-name}")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Consuming payment confirmation ...");

        var notification = Notification.builder()
                .type(NotificationType.PAYMENT_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .paymentConfirmation(paymentConfirmation)
                .build();

        repository.save(notification);

        var customerName = paymentConfirmation.customerFirstName() + " " + paymentConfirmation.customerLastName();


        emailService.sendEmailPaymentSuccess(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );
    }

    @KafkaListener(topics = "${application.kafka.order-topic-name}")
    public void consumeOrderSuccessNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Consuming order confirmation ...");

        var notification = Notification.builder()
                .type(NotificationType.ORDER_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .orderConfirmation(orderConfirmation)
                .build();

        repository.save(notification);

        var customerName = "Anonymous Customer";

        if (orderConfirmation.customer() != null)
            customerName = orderConfirmation.customer().firstName() + " " + orderConfirmation.customer().lastName();

        emailService.sendEmailOrderSuccess(
                orderConfirmation.customer() != null ? orderConfirmation.customer().email() : null,
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }
}
