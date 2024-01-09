package com.ify.test.integration.controller.paystack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ify.test.integration.dto.paystack.customer.CustomerRequestData;
import com.ify.test.integration.dto.paystack.customer.CustomerResponseData;
import com.ify.test.integration.services.paystack.customer.PayStackCustomerservice;

@RestController
@RequestMapping("/paystack")
public class CustomerController {

    private PayStackCustomerservice customerservice;

    @Autowired
    public CustomerController( final PayStackCustomerservice customerservice){
        this.customerservice = customerservice;
    }

    @PostMapping("/createCustomer")
    public ResponseEntity<CustomerResponseData> createCustomer(@RequestBody final CustomerRequestData data){
         CustomerResponseData response = this.customerservice.createCustomer(data);
        if(response != null){
           return new ResponseEntity<CustomerResponseData>(response, null, HttpStatus.CREATED);
        }
        else throw new NullPointerException("request failed");
 
    }

    @GetMapping("listCustomers")
    public ResponseEntity<Object> listCustomers(){
        Object response = this.customerservice.listCustomers();
        return new ResponseEntity<>(response, null, HttpStatus.OK);
    }


    
}
