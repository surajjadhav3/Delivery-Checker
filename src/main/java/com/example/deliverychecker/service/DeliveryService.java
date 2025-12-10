package com.example.deliverychecker.service;

import com.example.deliverychecker.util.PostalCodeLoader;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    private final PostalCodeLoader loader;

    public DeliveryService(PostalCodeLoader loader) {
        this.loader = loader;
    }

    public boolean isDeliverable(String postalCode) {
        if (postalCode == null) return false;
        return loader.isServiceable(postalCode.trim());
    }
}
