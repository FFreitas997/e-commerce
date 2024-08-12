package com.ffreitas.ecommerce.orderline;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-lines")
@RequiredArgsConstructor
public class OrderLineController {

    private final OrderLineService service;

    @GetMapping("/order/{order-id}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderLineResponse> getOrderLineByOrderId(@PathVariable("order-id") Integer orderId) {
        return service.getOrderLineByOrderId(orderId);
    }
}
