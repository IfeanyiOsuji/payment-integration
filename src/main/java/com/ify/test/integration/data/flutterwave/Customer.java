package com.ify.test.integration.data.flutterwave;

import jakarta.persistence.Entity;
import lombok.Data;


@Data
public class Customer {
    
    private String name;
    private String email;
    private String phonenumber;
}
