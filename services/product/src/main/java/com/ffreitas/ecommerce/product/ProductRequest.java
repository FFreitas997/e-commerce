package com.ffreitas.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link Product}
 */

public record ProductRequest(

        Integer id,

        @NotNull(message = "Product name is required")
        String name,

        @NotNull(message = "Product description is required")
        String description,

        @Positive(message = "Product quantity must be greater than 0")
        Double availableQuantity,

        @Positive(message = "Product price must be greater than 0")
        BigDecimal price,

        @NotNull(message = "Product category is required")
        Integer categoryID

) implements Serializable {

    /**
     * DTO for {@link com.ffreitas.ecommerce.category.Category}
     */

    public record CategoryRequest(

            Integer id,

            String name,

            String description

    ) implements Serializable { }
}