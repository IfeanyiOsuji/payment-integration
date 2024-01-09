package com.ify.test.integration.dto.paystack.customer;

import java.util.List;

import lombok.Data;

@Data
public class CustomerData {
    private String email;
    private int integration;
    private String domain;
    private String customer_code;
    private int id;
    private boolean identified;
    private List<String> identifications; 
    private String createdAt;
    private String updatedAt;

    
}
