package com.ify.test.integration.controller.flutterwave;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import com.ify.test.integration.data.flutterwave.Payload;
import com.ify.test.integration.dto.flutterwave.PaymentResponse;
import com.ify.test.integration.services.flutterwave.FlutterwavePaymentService;
import com.ify.test.integration.services.flutterwave.PaymentService;

@RestController
@RequestMapping("/flutterwave")
public class FlutterwavePaymentController {


    private final PaymentService paymentService;// = new FlutterwavePaymentService(new RestTemplate());

    public FlutterwavePaymentController(@Autowired final PaymentService paymentService){
            this.paymentService = paymentService;
    }



    @PostMapping("/pay")
    public String redirectToPaymentPortal(Payload payload){
        PaymentResponse response = this.paymentService.makePayment(payload);
        if(response.getStatus().equals("success")){
            String url = response.getData().get("link");
            System.out.println("message "+response.getMessage());
            return "redirect:" + url;
        }
        else throw new NullPointerException("request failed");

    }

    // @GetMapping("/payment-callback")
    // public String handlePaymentCallback(
    //         @RequestParam String status,
    //         @RequestParam String tx_ref,
    //         @RequestParam String transaction_id
    // ) {
    //     if ("successful".equals(status)) {
    //         // Assuming Transaction is a model/entity and TransactionService handles logic
    //         Transaction transactionDetails = transactionService.findByRef(tx_ref);

    //         // Assuming flw.Transaction.verify returns a response object
    //         TransactionResponse response = flwTransactionService.verifyTransaction(transaction_id);

    //         if (response.getData().getStatus().equals("successful")
    //                 && response.getData().getAmount() == transactionDetails.getAmount()
    //                 && response.getData().getCurrency().equals("NGN")) {
    //             // Success! Confirm the customer's payment
    //             return "Payment successful";
    //         } else {
    //             // Inform the customer their payment was unsuccessful
    //             return "Payment unsuccessful";
    //         }
    //     } else {
    //         return "Invalid status";
    //     }
    // }
    
}
