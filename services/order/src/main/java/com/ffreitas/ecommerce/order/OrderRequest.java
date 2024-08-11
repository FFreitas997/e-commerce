package com.ffreitas.ecommerce.order;

import com.ffreitas.ecommerce.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * DTO for {@link Order}
 */

public record OrderRequest(

        Integer id,

        String reference,

        @Positive(message = "Total amount must be greater than zero")
        BigDecimal totalAmount,

        @NotNull(message = "Payment method is required")
        PaymentMethod paymentMethod,

        @NotNull(message = "Customer ID is required")
        @NotEmpty(message = "Customer ID is required")
        @NotBlank(message = "Customer ID is required")
        String customerID,

        @NotEmpty(message = "Products are required")
        List<PurchaseRequest> products

) implements Serializable {
}