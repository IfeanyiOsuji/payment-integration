package com.ify.test.integration.dto.paystack.transactions;

import lombok.Data;

@Data
public class DataObj {
    private String authorization_url;
    private String access_code;
    private String reference;
    
}
