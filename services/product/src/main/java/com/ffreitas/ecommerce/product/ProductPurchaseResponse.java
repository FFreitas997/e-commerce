package com.ffreitas.ecommerce.product;

import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
public record ProductPurchaseResponse(

        Integer productID,

        String name,

        String description,

        BigDecimal price,

        double quantity

) implements Serializable {
}
