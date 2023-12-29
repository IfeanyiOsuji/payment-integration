package com.ify.test.integration.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class PaymentResponse {
    private String status;
    private String message;
    private Map<String, String> data = new HashMap<>();
}
