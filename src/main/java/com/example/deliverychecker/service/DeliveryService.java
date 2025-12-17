package com.example.deliverychecker.service;

import com.example.deliverychecker.exception.InvalidPostalCodeException;
import com.example.deliverychecker.exception.ServiceNotAvailableException;
import com.example.deliverychecker.util.PostalCodeLoader;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    private final PostalCodeLoader loader;

    public DeliveryService(PostalCodeLoader loader) {
        this.loader = loader;
    }

    public boolean isDeliverable(String postalCode) {

        // 1. Null or empty
        if (postalCode == null || postalCode.trim().isEmpty()) {
            throw new InvalidPostalCodeException("Postal code cannot be empty");
        }

        String cleaned = postalCode.trim();

        // 2. Must be numeric (optional rule)
        if (!cleaned.matches("\\d+")) {
            throw new InvalidPostalCodeException("Postal code must contain only numbers");
        }

        // 3. Check service availability
        boolean available = loader.isServiceable(cleaned);

        if (!available) {
            throw new ServiceNotAvailableException("Service not available for postal code: " + cleaned);
        }

        return true;
    }
}
