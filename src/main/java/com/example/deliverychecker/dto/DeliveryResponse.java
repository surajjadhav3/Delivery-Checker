package com.example.deliverychecker.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeliveryResponse {
    private boolean deliverable;
    private String message;

    public DeliveryResponse() {}
    public DeliveryResponse(boolean deliverable, String message) {
        this.deliverable = deliverable;
        this.message = message;
    }

}
