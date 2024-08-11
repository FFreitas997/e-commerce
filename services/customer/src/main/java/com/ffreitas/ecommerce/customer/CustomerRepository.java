package com.ffreitas.ecommerce.customer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    @Override
    boolean existsById(@NonNull String s);
}
