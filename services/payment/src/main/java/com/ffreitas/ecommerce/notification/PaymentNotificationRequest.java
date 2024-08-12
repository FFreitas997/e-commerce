package com.ffreitas.ecommerce.notification;

import com.ffreitas.ecommerce.payment.PaymentMethod;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentNotificationRequest(

        String orderReference,

        BigDecimal amount,

        PaymentMethod paymentMethod,

        String customerFirstName,

        String customerLastName,

        String customerEmail

) { }
