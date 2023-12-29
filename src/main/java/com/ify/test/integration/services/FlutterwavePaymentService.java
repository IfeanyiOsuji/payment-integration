package com.ify.test.integration.services;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import com.ify.test.integration.data.Customer;
import com.ify.test.integration.data.Customization;
import com.ify.test.integration.data.Payload;
import com.ify.test.integration.dto.PaymentResponse;

@Service
public class FlutterwavePaymentService implements PaymentService {

    @Value("${fluterwave.public-key}")
    private String publicKey;
    @Value("${flutterwave.secret-key}")
    private String privateKey = "FLWSECK_TEST-7c565cc3b057f3eb4e2680038d9adb55-X";

    //@Autowired
    private final RestTemplate restTemplate;
     

        String aprurl = "https://api.flutterwave.com/v3";

    public FlutterwavePaymentService(final RestTemplate restTemplate){
        this.restTemplate  = restTemplate;
    }

    public PaymentResponse makePayment(final Payload json){
        addRaveDetailsToPayload(json);
        String url = aprurl + "/payments";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+privateKey);
        System.out.println(headers.get("Authorization"));

        HttpEntity<Payload>httpEntity = new HttpEntity<>(json, headers);
        ResponseEntity<PaymentResponse> response = restTemplate.postForEntity(url, httpEntity, PaymentResponse.class);
        
        return response.getBody();
    }


    private void addRaveDetailsToPayload(Payload payload) {
        String txRef = UUID.randomUUID().toString();
        payload.setPublic_key(publicKey);
        payload.setTx_ref(txRef);
        payload.setRedirect_url("/callback");
    }


    
    public Map<String, Object> verifyTransaction(String transactionId) {
       
           String url = "https://api.flutterwave.com/v3/transactions/" + transactionId + "/verify";
        RestTemplate rest = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(privateKey);
        HttpEntity<String> httpEntity = new HttpEntity<>( headers);
        ParameterizedTypeReference<Map<String, Object>> typeRef = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<Map<String, Object>> response = rest.exchange(
                url, HttpMethod.GET, httpEntity, typeRef);
        return response.getBody();
    }


   public static void main(String[] args) {
    Customer customer = new Customer();
    customer.setEmail("oindubuisi@gmail.com");
    customer.setName("Ifeayi osuji");
    customer.setPhonenumber("08037355772");

    Customization customization = new Customization();
    customization.setTitle(" new payment");
    customization.setLogo("null");

     Payload json = new Payload();
        json.setAmount("100");
        json.setCurrency("NGN");
        json.setCustomer(customer);
        json.setCustomization(customization);

        FlutterwavePaymentService flutterwavePaymentService = new FlutterwavePaymentService(new RestTemplate());
        PaymentResponse response = flutterwavePaymentService.makePayment(json);
        System.out.println(response.toString());
   }
    

    
    
}
