package com.ify.test.integration.data.paystack;

import java.util.UUID;

//import javax.persistence.*;

import jakarta.persistence.*;
import lombok.Data;


//@Entity
//@Table(name = "customer")
@Data
public class Customer {
    //@Id
    private String id;
    private long payStackId;
    private String firstName;
    private String lastName;
    private String email;
    private String customerCode;
    private String phone;
    private String riskAction;
    private String createdAt;
    private String updatedAt;
    public Customer(){
        this.id = UUID.randomUUID().toString().substring(0, 20);
    }
    
}
