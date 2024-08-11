package com.ffreitas.ecommerce.orderline;

import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link OrderLine}
 */

@Builder
public record OrderLineResponse(

        Integer id,

        Integer productID,

        double quantity

) implements Serializable { }