package com.ffreitas.ecommerce.payment;

import com.ffreitas.ecommerce.customer.CustomerResponse;
import com.ffreitas.ecommerce.order.PaymentMethod;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentRequest(

        BigDecimal amount,

        PaymentMethod paymentMethod,

        Integer orderId,

        String orderReference,

        CustomerResponse customer

) { }
