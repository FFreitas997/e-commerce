package com.ffreitas.ecommerce.kafka.payment;

import com.ffreitas.ecommerce.kafka.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentConfirmation(

        String orderReference,

        BigDecimal amount,

        PaymentMethod paymentMethod,

        String customerFirstName,

        String customerLastName,

        String customerEmail

) { }
