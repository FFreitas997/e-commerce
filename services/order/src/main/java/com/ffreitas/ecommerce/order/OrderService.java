package com.ffreitas.ecommerce.order;

import com.ffreitas.ecommerce.customer.CustomerClient;
import com.ffreitas.ecommerce.exception.BusinessException;
import com.ffreitas.ecommerce.product.ProductClient;
import com.ffreitas.ecommerce.product.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;

    /**
     * This method creates an order.
     * It communicates with the customer service to get the customer information and
     * with the product service to get the product information.
     * Communication with microservice Customer is done using Feign Clients.
     * Communication with microservice Product is done using Rest Template.
     */
    public Integer createOrder(OrderRequest request) {
        var customer = customerClient
                .findCustomerByID(request.customerID())
                .orElseThrow(() -> new BusinessException("Cannot create order: Customer Not Found"));

        var res = productClient.purchaseProducts(request.products());

        var order = repository.save(mapper.toEntity(request));

        for (PurchaseRequest purchaseRequest : request.products()) {

        }
    }
}
