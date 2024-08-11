package com.ffreitas.ecommerce.order;

import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order toEntity(OrderRequest request) {
        return Order.builder()
                .id(request.id())
                .customerID(request.customerID())
                .reference(request.reference())
                .totalAmount(request.totalAmount())
                .paymentMethod(request.paymentMethod())
                .build();
    }

    public OrderResponse toResponse(Order order) {
        return OrderResponse
                .builder()
                .id(order.getId())
                .customerID(order.getCustomerID())
                .reference(order.getReference())
                .totalAmount(order.getTotalAmount())
                .paymentMethod(order.getPaymentMethod())
                .build();
    }
}
