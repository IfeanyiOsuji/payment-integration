package com.ify.test.integration.services;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ify.test.integration.data.Payload;
import com.ify.test.integration.dto.PaymentResponse;

@Service
public interface PaymentService {
    public PaymentResponse makePayment(final Payload json);
    
    
}
