package com.ify.test.integration.dto.paystack.transactions;

import lombok.Data;

@Data
public class TransactionInitializationResponse {
    private boolean status;
    private String message;
    private DataObj data;


}


