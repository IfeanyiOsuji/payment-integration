package com.ify.test.integration.data;

import lombok.Data;

@Data
public class Payload {
    
    private String amount;
    
    private String currency;
    
    private String country;
    
    private String description;
    
    private String payment_method;
    
    private String public_key;
    
    private String tx_ref;
    
    private String redirect_url;
    
    private Customer customer;
    private Customization customization;
}
