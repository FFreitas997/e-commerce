package com.ffreitas.ecommerce.customer;

public record CustomerResponse(

        String id,

        String firstName,

        String lastName,

        String email

) { }
