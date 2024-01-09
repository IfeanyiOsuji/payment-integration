package com.ify.test.integration.controller.paystack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ify.test.integration.dto.paystack.transactions.TransactionInitializationRequest;
import com.ify.test.integration.dto.paystack.transactions.TransactionInitializationResponse;
import com.ify.test.integration.services.paystack.transactions.TransactionService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/paystack/transaction")
public class TransactionsController {

    private TransactionService service;

    @Autowired
    public TransactionsController(final TransactionService service){
        this.service = service;

    }

    @PostMapping("/initailize")
    public ResponseEntity<TransactionInitializationResponse> createTransaction(@RequestBody final TransactionInitializationRequest request){
        TransactionInitializationResponse response = service.createTransaction(request);
        return new ResponseEntity< TransactionInitializationResponse>(response, null, HttpStatus.CREATED);

    }

    @GetMapping("/verify/{reference}")
    public ResponseEntity<Object> verifyTransaction(@PathVariable final String reference){
        Object response = service.verifyTransactions(reference);
         return new ResponseEntity<Object>(response, null, HttpStatus.OK);

    }
    
}
