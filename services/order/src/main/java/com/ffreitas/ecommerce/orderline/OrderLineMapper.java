package com.ffreitas.ecommerce.orderline;

import com.ffreitas.ecommerce.order.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderLineMapper {


    public OrderLine toEntity(OrderLineRequest request) {
        return OrderLine
                .builder()
                .id(request.id())
                .quantity(request.quantity())
                .productID(request.productID())
                .order(toOrder(request.orderID()))
                .build();
    }

    private Order toOrder(Integer integer) {
        return Order
                .builder()
                .id(integer)
                .build();
    }

    public OrderLineResponse toResponse(OrderLine orderLine) {
        return OrderLineResponse
                .builder()
                .id(orderLine.getId())
                .quantity(orderLine.getQuantity())
                .productID(orderLine.getProductID())
                .build();
    }
}
