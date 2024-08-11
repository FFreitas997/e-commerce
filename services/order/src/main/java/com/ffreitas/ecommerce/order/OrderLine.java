package com.ffreitas.ecommerce.order;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class OrderLine {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer productID;

    private double quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
