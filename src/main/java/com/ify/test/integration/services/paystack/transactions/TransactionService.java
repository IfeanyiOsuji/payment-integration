package com.ify.test.integration.services.paystack.transactions;

import org.springframework.stereotype.Service;

import com.ify.test.integration.dto.paystack.transactions.TransactionInitializationRequest;
import com.ify.test.integration.dto.paystack.transactions.TransactionInitializationResponse;

@Service
public interface TransactionService {
    public TransactionInitializationResponse createTransaction(TransactionInitializationRequest request);
    public Object verifyTransactions(String reference);
    public Object listTransactions();
    public Object fetctTransactions();
}
