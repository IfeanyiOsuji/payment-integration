package com.ify.test.integration.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    private final RestTemplate restTemplate;

    @Autowired
    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Reusable method for GET request
    public <T> ResponseEntity<T> getRequest(String url, Class<T> responseType, String token) {
         HttpHeaders headers = new HttpHeaders();
         headers.set("Authorization", "Bearer "+token);
         HttpEntity<?> requestEntity = new HttpEntity<>(null, headers);
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                responseType
        );
    }

    // Reusable method for POST request
    public <T, R> ResponseEntity<T> postRequest(String url, R requestBody, Class<T> responseType, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
          headers.set("Authorization", "Bearer "+token);
        HttpEntity<R> requestEntity = new HttpEntity<>(requestBody, headers);

        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                responseType
        );
    }

    // Reusable method for PUT request
    public <T, R> ResponseEntity<T> putRequest(String url, R requestBody, Class<T> responseType, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
          headers.set("Authorization", "Bearer "+token);
        HttpEntity<R> requestEntity = new HttpEntity<>(requestBody, headers);

        return restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                responseType
        );
    }

    // Reusable method for DELETE request
    public ResponseEntity<Void> deleteRequest(String url, String token) {
         HttpHeaders headers = new HttpHeaders();
         headers.set("Authorization", "Bearer "+token);
        return restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                Void.class
        );
    }
    
}
