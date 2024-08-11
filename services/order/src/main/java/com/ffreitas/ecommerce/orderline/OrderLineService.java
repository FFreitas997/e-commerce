package com.ffreitas.ecommerce.orderline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest request) {
        var orderLine = mapper.toEntity(request);
        return repository.save(orderLine).getId();
    }

    public List<OrderLineResponse> getOrderLineByOrderId(Integer orderId) {
        return repository.findByOrder_Id(orderId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
