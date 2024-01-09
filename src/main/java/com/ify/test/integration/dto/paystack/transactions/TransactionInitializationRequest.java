
package com.ify.test.integration.dto.paystack.transactions;

import lombok.Data;

@Data
public class TransactionInitializationRequest{

    private String email;
    private String amount;
}