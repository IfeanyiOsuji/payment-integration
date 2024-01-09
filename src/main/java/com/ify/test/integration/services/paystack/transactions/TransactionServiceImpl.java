package com.ify.test.integration.services.paystack.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.ify.test.integration.dto.paystack.transactions.TransactionInitializationRequest;
import com.ify.test.integration.dto.paystack.transactions.TransactionInitializationResponse;
import com.ify.test.integration.services.ApiService;


@Service
public class TransactionServiceImpl implements TransactionService{
    private ApiService apiService;

    @Value("${paystack.secret-key}")
    private String secretekey;

    @Value("${paystack.apiurl}")
    private String apiUrl;

    @Autowired
    public TransactionServiceImpl(ApiService apiService){
        this.apiService = apiService;
    }

    @Override
    public TransactionInitializationResponse createTransaction(TransactionInitializationRequest request) {
        String url = apiUrl + "/transaction/initialize";
        ResponseEntity<TransactionInitializationResponse> response = apiService.postRequest(url, request, TransactionInitializationResponse.class, secretekey);
        // TODO Auto-generated method stub
        if(response.getStatusCode().value() == 200){
            return response.getBody();
        }
        throw new HttpClientErrorException(response.getStatusCode());
    }

    @Override
    public Object verifyTransactions(String reference) {
         String url = apiUrl + "/transaction/verify/"+reference;
         System.out.println("url "+url);
         ResponseEntity<Object> response = apiService.getRequest(url, Object.class, secretekey);

         if(response.getStatusCode().value() == 200){
            return response.getBody();
        }
        throw new HttpClientErrorException(response.getStatusCode());
    }

    @Override
    public Object listTransactions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listTransactions'");
    }

    @Override
    public Object fetctTransactions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fetctTransactions'");
    }
    
}
