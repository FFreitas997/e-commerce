package com.ffreitas.ecommerce.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createProduct(@RequestBody @Valid ProductRequest request) {
        return service.createProduct(request);
    }

    @PostMapping("/purchase")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductPurchaseResponse> purchaseProducts(@RequestBody List<ProductPurchaseRequest> request) {
        return service.purchaseProducts(request);
    }

    @GetMapping("/{product-id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse findByID(@PathVariable("product-id") Integer id) {
        return service.getProductByID(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return service.getAllProducts();
    }
}
