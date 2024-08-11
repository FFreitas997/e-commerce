package com.ffreitas.ecommerce.product;

import com.ffreitas.ecommerce.category.Category;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String description;

    private Double availableQuantity;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
