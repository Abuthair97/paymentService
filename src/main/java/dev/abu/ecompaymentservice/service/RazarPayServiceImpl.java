package dev.abu.ecompaymentservice.service;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import dev.abu.ecompaymentservice.config.RazorpayConfig;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service("razarPay")
public class RazarPayServiceImpl implements PaymentService{

    private RazorpayClient razorpayClient;
    public RazarPayServiceImpl(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }
    @Override
    public String doPayment(String email, String phoneNumber, Long amount, String orderId) throws RazorpayException {
     try{
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount", amount);
        paymentLinkRequest.put("currency", "INR");
        paymentLinkRequest.put("receipt", orderId);
        JSONObject customers = new JSONObject();
        customers.put("email", email);
        customers.put("contact", phoneNumber);
        paymentLinkRequest.put("customer", customers);

        JSONObject notify = new JSONObject();
        notify.put("sms", true);
        notify.put("email", true);
        paymentLinkRequest.put("notify", notify);
        paymentLinkRequest.put("callback_url", "http://localhost:8080//RazorpayWebhook");
        paymentLinkRequest.put("callback_method", "post");

        PaymentLink response  = razorpayClient.paymentLink.create(paymentLinkRequest);
        return response.toString();
   }
    catch(RazorpayException e){
         throw new RazorpayException(e.getMessage());
    }
     }
}
