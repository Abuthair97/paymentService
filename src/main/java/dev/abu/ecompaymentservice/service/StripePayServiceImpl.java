package dev.abu.ecompaymentservice.service;

import org.springframework.stereotype.Service;

@Service("stripePay")
public class StripePayServiceImpl implements PaymentService {
    @Override
    public String doPayment(String email, String phoneNumber, Long amount, String orderId) {
        return null;
    }
}
