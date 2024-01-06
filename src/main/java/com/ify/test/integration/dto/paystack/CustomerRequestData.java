package com.ify.test.integration.dto.paystack;

import lombok.Data;

@Data
public class CustomerRequestData {
    private String email;
    private String phone;
    private String first_name;
    private String last_name;
    
}
