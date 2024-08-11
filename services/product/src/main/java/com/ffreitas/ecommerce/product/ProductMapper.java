package com.ffreitas.ecommerce.product;

import com.ffreitas.ecommerce.category.Category;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequest request) {
        if (request == null)
            return null;

        return Product
                .builder()
                .name(request.name())
                .description(request.description())
                .availableQuantity(request.availableQuantity())
                .price(request.price())
                .category(
                        Category
                                .builder()
                                .id(request.categoryID())
                                .build()
                )
                .build();
    }

    public ProductResponse toResponse(Product product) {
        if (product == null)
            return null;

        return ProductResponse
                .builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .availableQuantity(product.getAvailableQuantity())
                .price(product.getPrice())
                .category(toCategoryResponse(product.getCategory()))
                .build();
    }

    private ProductResponse.CategoryResponse toCategoryResponse(Category category) {
        if (category == null)
            return null;

        return ProductResponse.CategoryResponse
                .builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    public ProductPurchaseResponse toPurchaseResponse(Product storedProduct, double quantity) {
        if (storedProduct == null)
            return null;

        return ProductPurchaseResponse
                .builder()
                .productID(storedProduct.getId())
                .name(storedProduct.getName())
                .description(storedProduct.getDescription())
                .quantity(quantity)
                .price(storedProduct.getPrice())
                .build();
    }
}
