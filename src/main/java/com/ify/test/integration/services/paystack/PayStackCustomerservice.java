package com.ify.test.integration.services.paystack;

import org.springframework.stereotype.Service;

import com.ify.test.integration.dto.paystack.CustomerRequestData;
import com.ify.test.integration.dto.paystack.CustomerResponseData;


@Service
public interface PayStackCustomerservice {

    
    public CustomerResponseData createCustomer(CustomerRequestData data);
    
}
