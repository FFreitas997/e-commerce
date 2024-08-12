package com.ffreitas.ecommerce.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link Payment}
 */

public record PaymentRequest(

        Integer id,

        BigDecimal amount,

        PaymentMethod paymentMethod,

        Integer orderId,

        String orderReference,

        Customer customer

) implements Serializable {

    public record Customer(

            String id,

            @NotNull(message = "Customer first name is required")
            String firstName,

            @NotNull(message = "Customer last name is required")
            String lastName,

            @NotNull(message = "Customer email is required")
            @Email(message = "Customer email is invalid")
            String email

    ) implements Serializable {
    }

}