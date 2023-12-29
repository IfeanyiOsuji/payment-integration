package com.ify.test.integration.data;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Customer {
    
    private String name;
    private String email;
    private String phonenumber;
}
