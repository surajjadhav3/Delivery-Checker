package com.example.deliverychecker.controller;

import com.example.deliverychecker.dto.AddressRequest;
import com.example.deliverychecker.dto.DeliveryResponse;
import com.example.deliverychecker.service.DeliveryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/check")
    public ResponseEntity<DeliveryResponse> checkDeliverable(@Valid @RequestBody AddressRequest request) {
        boolean ok = deliveryService.isDeliverable(request.getPostalCode());
        System.out.println("Received request: " + request.getPostalCode());
        System.out.println("Received request: " + request.getPostalCode());


        if (ok) {
            return ResponseEntity.ok(new DeliveryResponse(true,
                    "Product is deliverable at postal code " + request.getPostalCode()));
        } else {
            return ResponseEntity.ok(new DeliveryResponse(false,
                    "Delivery is not available for this location"));
        }
    }
}
