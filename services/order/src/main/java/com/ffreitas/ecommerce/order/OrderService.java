package com.ffreitas.ecommerce.order;

import com.ffreitas.ecommerce.customer.CustomerClient;
import com.ffreitas.ecommerce.exception.BusinessException;
import com.ffreitas.ecommerce.kafka.NotificationService;
import com.ffreitas.ecommerce.kafka.OrderConfirmation;
import com.ffreitas.ecommerce.orderline.OrderLineRequest;
import com.ffreitas.ecommerce.orderline.OrderLineService;
import com.ffreitas.ecommerce.product.ProductClient;
import com.ffreitas.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final NotificationService notificationService;

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

        var purchaseProducts = productClient.purchaseProducts(request.products());

        var order = repository.save(mapper.toEntity(request));

        for (PurchaseRequest purchaseRequest : request.products()) {
            var requestOrderLine = OrderLineRequest
                    .builder()
                    .id(null)
                    .orderID(order.getId())
                    .productID(purchaseRequest.productID())
                    .quantity(purchaseRequest.quantity())
                    .build();

            var orderLineID = orderLineService.saveOrderLine(requestOrderLine);
        }

        // TODO: Start Payment Process

        var orderConfirmation = OrderConfirmation
                .builder()
                .orderReference(request.reference())
                .totalAmount(request.totalAmount())
                .paymentMethod(request.paymentMethod())
                .customer(customer)
                .products(purchaseProducts)
                .build();
        notificationService.sendOrderConfirmation(orderConfirmation);

        return order.getId();
    }

    public List<OrderResponse> getAllOrders() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public OrderResponse getOrderById(Integer id) {
        return repository
                .findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
    }
}
