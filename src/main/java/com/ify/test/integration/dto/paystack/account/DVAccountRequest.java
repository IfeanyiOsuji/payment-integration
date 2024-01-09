package com.ify.test.integration.dto.paystack.account;

import lombok.Data;

@Data
public class DVAccountRequest {
    private String customer;
    private String preferred_bank;
}
