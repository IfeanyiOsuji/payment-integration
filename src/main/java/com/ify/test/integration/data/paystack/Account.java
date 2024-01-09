package com.ify.test.integration.data.paystack;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
//@Entity
public class Account {
    //@Id
    private String id = UUID.randomUUID().toString().substring(0, 21);
    private Customer customer;
    private String accountName;
    private String accountNumber;
    private String assigned;
    private String curency;
    private boolean active;
    private int accountId;
    private String createdAt;
    private String updatedAt;
    private long assigneeId;
    private boolean expired;
    private String assigneeType;
    private String assignedAt;
    private String accountType;

    
}


