package com.ify.test.integration.services.flutterwave;

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

import com.ify.test.integration.data.flutterwave.Customer;
import com.ify.test.integration.data.flutterwave.Customization;
import com.ify.test.integration.data.flutterwave.Payload;
import com.ify.test.integration.dto.flutterwave.PaymentResponse;

@Service
public class FlutterwavePaymentService implements PaymentService {

    @Value("${flutterwave.public-key}")
    private String publicKey;
    @Value("${flutterwave.secret-key}")
    private String privateKey;

    //@Autowired
    private final RestTemplate restTemplate;
     
        @Value("${flutterwave.apiurl}")
        String aprurl;

    public FlutterwavePaymentService(){
        this.restTemplate  = new RestTemplate();
    }

    public PaymentResponse makePayment(final Payload json){
        addRaveDetailsToPayload(json);
        String url = aprurl + "/payments";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+privateKey);
        //System.out.println(headers.get("Authorization"));
        //System.out.println(json.getTx_ref());

        HttpEntity<Payload>httpEntity = new HttpEntity<>(json, headers);
        ResponseEntity<PaymentResponse> response = restTemplate.postForEntity(url, httpEntity, PaymentResponse.class);
        
        return response.getBody();
    }

   


    private void addRaveDetailsToPayload(Payload payload) {
        String txRef = UUID.randomUUID().toString().substring(0, 20);
        payload.setPublic_key(publicKey);
        payload.setTx_ref(txRef);
        payload.setRedirect_url("/payment-callback");
    }


    
    public Map<String, Object> verifyTransaction(String transactionId) {
       
           String url = aprurl+"/transactions/" + transactionId + "/verify";
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

    
}
