package com.ify.test.integration.services.paystack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ify.test.integration.dto.paystack.CustomerRequestData;
import com.ify.test.integration.dto.paystack.CustomerResponseData;
import com.ify.test.integration.services.ApiService;

@Service
public class PayStackCustomerServiceImpl implements PayStackCustomerservice {

    private ApiService apiService;

    @Value("${paystack.secret-key}")
    private String secretekey;

    @Value("${paystack.apiurl}")
    private String apiUrl;
    
    @Autowired
    public PayStackCustomerServiceImpl(final ApiService apiService){
        this.apiService = apiService;
    } 

    @Override
    public CustomerResponseData createCustomer(CustomerRequestData requestData) {
        System.out.println(requestData.toString());
            String url = apiUrl + "/customer";
            ResponseEntity<CustomerResponseData>response = apiService.postRequest(url, requestData, CustomerResponseData.class, secretekey);
        return response.getBody();
        
    }
    
}
