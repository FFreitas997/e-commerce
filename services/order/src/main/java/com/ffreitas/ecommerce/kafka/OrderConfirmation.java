package com.ffreitas.ecommerce.kafka;

import com.ffreitas.ecommerce.customer.CustomerResponse;
import com.ffreitas.ecommerce.order.PaymentMethod;
import com.ffreitas.ecommerce.product.PurchaseResponse;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record OrderConfirmation(

        String orderReference,

        BigDecimal totalAmount,

        PaymentMethod paymentMethod,

        CustomerResponse customer,

        List<PurchaseResponse> products

) {
}
