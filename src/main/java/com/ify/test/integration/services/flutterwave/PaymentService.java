package com.ify.test.integration.services.flutterwave;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ify.test.integration.data.flutterwave.Payload;
import com.ify.test.integration.dto.flutterwave.PaymentResponse;

@Service
public interface PaymentService {
    public PaymentResponse makePayment(final Payload json);
    
    
}
