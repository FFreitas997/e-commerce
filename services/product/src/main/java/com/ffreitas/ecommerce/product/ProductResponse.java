package com.ffreitas.ecommerce.product;

import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link Product}
 */

@Builder
public record ProductResponse(

        Integer id,

        String name,

        String description,

        Double availableQuantity,

        BigDecimal price,

        CategoryResponse category

) implements Serializable {

    /**
     * DTO for {@link com.ffreitas.ecommerce.category.Category}
     */

    @Builder
    public record CategoryResponse(

            Integer id,

            String name,

            String description

    ) implements Serializable { }
}