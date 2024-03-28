package dev.abu.ecompaymentservice.service;

import com.stripe.Stripe;
import com.stripe.StripeClient;
import com.stripe.model.PaymentLink;
import dev.abu.ecompaymentservice.config.StripeConfig;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("stripePay")
public class StripePayServiceImpl implements PaymentService {

    @Autowired
    private StripeConfig  stripeConfig;



    @Override
    public String doPayment(String email, String phoneNumber, Long amount, String orderId) {
        try{
            JSONObject paymentIntentParams = new JSONObject();
            paymentIntentParams.put("amount", amount);
            paymentIntentParams.put("currency", "INR");
            paymentIntentParams.put("receipt", orderId);
            JSONObject customer = new JSONObject();
            customer.put("email", email);
            customer.put("phone", phoneNumber);
            paymentIntentParams.put("customer", customer);
            JSONObject notify = new JSONObject();
            notify.put("sms",true);
            notify.put("email",true);
            paymentIntentParams.put("notify",notify);
            paymentIntentParams.put("callback_url", "http://localhost:8080/stripeWebHook");
            paymentIntentParams.put("callback_method", "post");

            Map<String, Object> mapTofunction = new HashMap<>(paymentIntentParams.toMap());
            Stripe.apiKey =stripeConfig.getStripeKeySecret();

            PaymentLink response = PaymentLink.create(mapTofunction);
            return response.toString();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
