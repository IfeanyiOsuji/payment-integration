package com.ify.test.integration.data.paystack;

import java.util.UUID;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Customer {
    private String id = UUID.randomUUID().toString().substring(0, 20);
    private long payStackId;
    private String firstName;
    private String lastName;
    private String email;
    private String customerCode;
    private String phone;
    private String riskAction;
    private String createdAt;
    private String updatedAt;
    
}
