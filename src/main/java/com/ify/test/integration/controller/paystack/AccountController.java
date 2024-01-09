package com.ify.test.integration.controller.paystack;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ify.test.integration.data.paystack.Account;
import com.ify.test.integration.dto.paystack.account.DVAccountRequest;
import com.ify.test.integration.services.paystack.account.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(final AccountService service){
        this.accountService = service;
    }

    @PostMapping("/create_dedicated_virtual_account")
    public ResponseEntity<Account>createDedicatedVirtualAccount(@RequestBody final DVAccountRequest request){
        System.out.println("request "+ request.toString());
        Account account = accountService.createDedicatedVirtualAccount(request);
        
        return new ResponseEntity<Account>(account, null, HttpStatus.CREATED);
    }
    
}
