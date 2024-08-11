package com.ffreitas.ecommerce.product;

import com.ffreitas.ecommerce.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductClient {

    @Value("${application.config.product-url}")
    private String productURL;
    private final RestTemplate restTemplate;


    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(request, headers);
        ParameterizedTypeReference<List<PurchaseResponse>> responseType =
                new ParameterizedTypeReference<>() {
                };

        ResponseEntity<List<PurchaseResponse>> response = restTemplate
                .exchange(productURL + "/purchase", HttpMethod.POST, requestEntity, responseType);

        if (response.getStatusCode().isError())
            throw new BusinessException("An error occurred while processing the products purchase: " + response.getStatusCode() + " status code");

        return response.getBody();
    }
}
