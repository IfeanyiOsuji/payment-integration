package com.ify.test.integration.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import com.ify.test.integration.data.Payload;
import com.ify.test.integration.dto.PaymentResponse;
import com.ify.test.integration.services.FlutterwavePaymentService;
import com.ify.test.integration.services.PaymentService;

@RestController
@RequestMapping("/flutterwave")
public class FlutterwavePaymentController {


    private final PaymentService paymentService;// = new FlutterwavePaymentService(new RestTemplate());

    public FlutterwavePaymentController(final PaymentService paymentService){
            this.paymentService = paymentService;
    }



    @PostMapping("/pay")
    public RedirectView redirectToPaymentPortal(Payload payload){
        PaymentResponse response = this.paymentService.makePayment(payload);
        if(response.getStatus().equals("success")){
            String url = response.getData().get("link");
            return new RedirectView(url);
        }
        else throw new NullPointerException("request failed");

    }
    
}
