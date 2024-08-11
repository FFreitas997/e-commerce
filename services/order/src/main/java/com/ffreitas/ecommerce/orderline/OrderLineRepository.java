package com.ffreitas.ecommerce.orderline;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    @Query("select o from OrderLine o where o.order.id = ?1")
    List<OrderLine> findByOrder_Id(@NonNull Integer id);
}
