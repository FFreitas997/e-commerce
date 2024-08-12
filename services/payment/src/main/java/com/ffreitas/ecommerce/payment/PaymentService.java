package com.ffreitas.ecommerce.payment;

import com.ffreitas.ecommerce.notification.NotificationService;
import com.ffreitas.ecommerce.notification.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;
    private final NotificationService notificationService;
    private final PaymentMapper mapper;

    public Integer createPayment(PaymentRequest request) {
        var savedPayment = repository.save(mapper.toEntity(request));

        var notifyRequest = PaymentNotificationRequest.builder()
                .orderReference(request.orderReference())
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .customerFirstName(request.customer() != null ? request.customer().firstName() : null)
                .customerLastName(request.customer() != null ? request.customer().lastName() : null)
                .customerEmail(request.customer() != null ? request.customer().email() : null)
                .build();
        notificationService.sendNotification(notifyRequest);

        return savedPayment.getId();
    }
}
