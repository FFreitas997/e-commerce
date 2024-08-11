package com.ffreitas.ecommerce.order;

import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link Order}
 */

@Builder
public record OrderResponse(

        Integer id,

        String reference,

        BigDecimal totalAmount,

        PaymentMethod paymentMethod,

        String customerID

) implements Serializable {
}