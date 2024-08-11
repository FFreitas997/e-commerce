package com.ffreitas.ecommerce.product;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Integer createProduct(ProductRequest request) {
        var product = mapper.toEntity(request);
        return repository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        var productsIDs = request
                .stream()
                .map(ProductPurchaseRequest::productID)
                .filter(Objects::nonNull)
                .toList();

        var storedProducts = repository.findAllByIdInOrderById(productsIDs);

        if (productsIDs.size() != storedProducts.size())
            throw new ProductPurchaseException("One or more products doesn't exist");

        var storesRequest = request.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productID))
                .toList();

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        for (int i = 0; i < storedProducts.size(); i++) {
            var storedProduct = storedProducts.get(i);
            var requestProduct = storesRequest.get(i);

            if (storedProduct.getAvailableQuantity() < requestProduct.quantity())
                throw new ProductPurchaseException("Product " + storedProduct.getName() + " doesn't have enough quantity");

            storedProduct.setAvailableQuantity(storedProduct.getAvailableQuantity() - requestProduct.quantity());
            repository.save(storedProduct);

            purchasedProducts.add(mapper.toPurchaseResponse(storedProduct, requestProduct.quantity()));
        }

        return purchasedProducts;
    }

    public ProductResponse getProductByID(Integer id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + id));
    }

    public List<ProductResponse> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
