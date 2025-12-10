package com.example.deliverychecker;

import com.example.deliverychecker.dto.AddressRequest;
import com.example.deliverychecker.dto.DeliveryResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeliveryControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testDeliverablePostalCode() {
        AddressRequest request = new AddressRequest();
        request.setStreet("MG Road");
        request.setCity("Pune");
        request.setState("Maharashtra");
        request.setCountry("India");
        request.setPostalCode("411001");

        ResponseEntity<DeliveryResponse> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/v1/delivery/check",
                request,
                DeliveryResponse.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        assertTrue(response.getBody().isDeliverable());
    }
}