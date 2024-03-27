package dev.abu.ecompaymentservice.controller;


import com.razorpay.RazorpayException;
import dev.abu.ecompaymentservice.dto.InitiatePaymentRequestDto;
import dev.abu.ecompaymentservice.service.PaymentService;
import dev.abu.ecompaymentservice.service.paymentSelectionStratergy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    private PaymentService razorPayService;
    private PaymentService stripePayService;
    private paymentSelectionStratergy selectionStratergy;

    public PaymentController(
            @Qualifier("razarPay") PaymentService razorPayService,
            @Qualifier("stripePay") PaymentService stripePayService,
            paymentSelectionStratergy selectionStratergy) {
        this.razorPayService = razorPayService;
        this.stripePayService = stripePayService;
        this.selectionStratergy = selectionStratergy;
    }

    @PostMapping("/payment")
    public String doPayment(@RequestBody InitiatePaymentRequestDto requestDto) throws RazorpayException {
        int gateway = ChooseGateway();
        if (gateway == 1) {
            return razorPayService.doPayment(requestDto.getEmail(), requestDto.getPhoneNumber(), requestDto.getAmount(), requestDto.getOrderId());
        } else {
            return stripePayService.doPayment(requestDto.getEmail(), requestDto.getPhoneNumber(), requestDto.getAmount(), requestDto.getOrderId());
        }
    }

    private int ChooseGateway() {
        return selectionStratergy.plainPaymentSelection();
    }
}