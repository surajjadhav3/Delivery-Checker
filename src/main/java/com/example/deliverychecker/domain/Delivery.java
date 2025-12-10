package com.example.deliverychecker.domain;

public class Delivery {
    private final String postalCode;

    public Delivery(String postalCode) {
        this.postalCode = postalCode;
    }

    public String postalCode() {
        return postalCode;
    }
}
