package com.ify.test.integration.dto.paystack.customer;




import lombok.Data;

@Data
public class CustomerResponseData {

    private boolean status;
    private String message;
    private CustomerData data;
    
}

