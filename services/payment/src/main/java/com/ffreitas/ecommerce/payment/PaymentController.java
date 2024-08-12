package com.ffreitas.ecommerce.payment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createPayment(@RequestBody @Valid PaymentRequest request) {
        return service.createPayment(request);
    }
}
