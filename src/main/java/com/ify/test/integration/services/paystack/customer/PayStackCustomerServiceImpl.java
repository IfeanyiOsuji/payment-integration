package com.ify.test.integration.services.paystack.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ify.test.integration.data.paystack.Customer;
import com.ify.test.integration.dto.paystack.customer.CustomerRequestData;
import com.ify.test.integration.dto.paystack.customer.CustomerResponseData;
//import com.ify.test.integration.repository.CustomerRepository;
import com.ify.test.integration.services.ApiService;

@Service
public class PayStackCustomerServiceImpl implements PayStackCustomerservice {

    private ApiService apiService;

    @Value("${paystack.secret-key}")
    private String secretekey;

    @Value("${paystack.apiurl}")
    private String apiUrl;

   // @Autowired
   // private CustomerRepository repository;
    
    @Autowired
    public PayStackCustomerServiceImpl(final ApiService apiService){
        this.apiService = apiService;
        
    } 
    //  @Autowired
    // public PayStackCustomerServiceImpl(final ApiService apiService, CustomerRepository repository){
    //     this.apiService = apiService;
    //     this.repository = repository;
    // } 

    @Override
    public CustomerResponseData createCustomer(CustomerRequestData requestData) {
        System.out.println(requestData.toString());
            String url = apiUrl + "/customer";
            ResponseEntity<CustomerResponseData>response = apiService.postRequest(url, requestData, CustomerResponseData.class, secretekey);
        CustomerResponseData data =  response.getBody();
        Customer customer = mapCustomerResponseDataToCustomer(data);
        customer.setFirstName(requestData.getFirst_name());
        customer.setLastName(requestData.getLast_name());
        customer.setPhone(requestData.getPhone());
        System.out.println("Ustomer data "+ customer.toString());
       // repository.save(customer);
        return data;
    }
    private Customer mapCustomerResponseDataToCustomer(final CustomerResponseData data){
        Customer customer = new Customer();
        customer.setPayStackId(data.getData().getId());
        customer.setFirstName(data.getData().getEmail());
        customer.setCustomerCode(data.getData().getCustomer_code());
        customer.setCreatedAt(data.getData().getCreatedAt());
        customer.setUpdatedAt(data.getData().getUpdatedAt());
        return customer;
    }
    /*
     * private long payStackId;
    private String firstName;
    private String lastName;
    private String email;
    private String customerCode;
    private String phone;
    private String riskAction;
    private String createdAt;
    private String updatedAt;



        "data": {
        "email": "oindubuisi@gmail.com",
        "integration": 1126893,
        "domain": "test",
        "customer_code": "CUS_utx8ttvbid41y4v",
        "id": 154457885,
        "identified": false,
        "identifications": null,
        "createdAt": "2024-01-06T13:36:39.000Z",
        "updatedAt": "2024-01-06T13:36:39.000Z"
    }
     */

    @Override
    public Object listCustomers() {
        String url = apiUrl + "/customer";
        ResponseEntity<Object> response = apiService.getRequest(url, Object.class, secretekey);
        return response.getBody();
    }
}
