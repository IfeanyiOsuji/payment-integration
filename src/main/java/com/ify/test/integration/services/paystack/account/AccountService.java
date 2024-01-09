package com.ify.test.integration.services.paystack.account;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.ify.test.integration.data.paystack.Account;
import com.ify.test.integration.dto.paystack.account.DVAccountRequest;
import com.ify.test.integration.dto.paystack.customer.CustomerResponseData;
//import com.ify.test.integration.repository.AccountRepository;
import com.ify.test.integration.services.ApiService;

@Service
public class AccountService {

    private ApiService apiService;

    @Value("${paystack.secret-key}")
    private String secretekey;

    @Value("${paystack.apiurl}")
    private String apiUrl;

   // private AccountRepository repository;
   @Autowired
    public AccountService (final ApiService apiService){
        this.apiService = apiService;
       // this.repository = repository;
    }

    public Account createDedicatedVirtualAccount(final DVAccountRequest data){
        
        String url = apiUrl +"/dedicated_account";
        
        ResponseEntity<HashMap>response = apiService.postRequest(url, data, HashMap.class,  secretekey);
       
        System.out.println("response "+ response.toString());
        Account account =  mapAccountFieldsToPayStackAccountObject(response.getBody());
        return account;

    }

    private Account mapAccountFieldsToPayStackAccountObject(final Map<String, Object>responseData){
            Account account = new Account();
            HashMap<String, Object>assignment = new HashMap<>();
            account.setAccountId((int)responseData.get("id"));
            account.setAccountName((String)responseData.get("account_name"));
            account.setAccountNumber((String)responseData.get("account_number"));
            account.setActive((boolean)responseData.get("active"));
            account.setAssigned((String)responseData.get("assigned"));
            account.setCreatedAt((String)responseData.get("created_at"));
            account.setUpdatedAt((String)responseData.get("updated_at"));
            account.setCurency((String)responseData.get("currency"));
            //account.setAssigneeId(responseData.get("assignment").get("assignee_id"));
            if(responseData.get("assignment") != null){
            assignment = (HashMap<String, Object>)responseData.get("assignment");
            account.setAssigneeId((long)assignment.get("assignee_id"));
            account.setAssigneeType((String)assignment.get("assignee_type"));
            account.setExpired((boolean)assignment.get("expired"));
            account.setAssignedAt((String)assignment.get("assigned_at"));
            account.setAccountType((String)assignment.get("account_type"));
            }

            return account;
    }
    
}
/*
 * "account_name": "KAROKART / RHODA CHURCH",

    "account_number": "9930000737",

    "assigned": true,

    "currency": "NGN",

    "metadata": null,

    "active": true,

    "id": 253,

    "created_at": "2019-12-12T12:39:04.000Z",

    "updated_at": "2020-01-06T15:51:24.000Z",

    "assignment": {

      "integration": 100043,

      "assignee_id": 7454289,

      "assignee_type": "Customer",

      "expired": false,

      "account_type": "PAY-WITH-TRANSFER-RECURRING",

      "assigned_at": "2020-01-06T15:51:24.764Z"

    },
 */
