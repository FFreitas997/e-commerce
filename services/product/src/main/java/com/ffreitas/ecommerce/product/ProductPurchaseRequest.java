package com.ffreitas.ecommerce.product;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record ProductPurchaseRequest(

        @NotNull(message = "Product ID is required")
        Integer productID,

        @NotNull(message = "Quantity is required")
        double quantity

) implements Serializable {
}
