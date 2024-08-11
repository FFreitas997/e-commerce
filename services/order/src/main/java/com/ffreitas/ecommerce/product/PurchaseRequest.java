package com.ffreitas.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

public record PurchaseRequest(

        @NotNull(message = "Product ID is required")
        Integer productID,

        @Positive(message = "Quantity must be greater than zero")
        double quantity

) implements Serializable { }
