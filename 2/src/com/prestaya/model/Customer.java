package com.prestaya.model;

public record Customer(
        Long id,
        String name,
        String creditScore,
        String city) {

}
