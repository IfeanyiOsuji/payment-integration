package com.ify.test.integration.services.paystack.customer;

import org.springframework.stereotype.Service;

import com.ify.test.integration.dto.paystack.customer.CustomerRequestData;
import com.ify.test.integration.dto.paystack.customer.CustomerResponseData;


@Service
public interface PayStackCustomerservice {

    
    public CustomerResponseData createCustomer(CustomerRequestData data);
    public Object listCustomers();
    
}
