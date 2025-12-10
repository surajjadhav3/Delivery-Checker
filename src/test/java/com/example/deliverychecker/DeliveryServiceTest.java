package com.example.deliverychecker;

import com.example.deliverychecker.util.PostalCodeLoader;
import com.example.deliverychecker.service.DeliveryService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeliveryServiceTest {

    @Test
    void testDeliverable() {
        PostalCodeLoader loader = new PostalCodeLoader();
        // Manually init allowed set via reflection or change PostalCodeLoader to accept resource in ctor for tests.
        // For simplicity, call isServiceable after loader.init (requires classpath resource exists)
        loader.init();
        DeliveryService svc = new DeliveryService(loader);
        assertTrue(svc.isDeliverable("411001"));
        assertFalse(svc.isDeliverable("999999"));
    }
}
