package dev.abu.ecompaymentservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stripeWebHook")
public class StripePayWebHookController {

    @PostMapping("/")
    public ResponseEntity acceptWebHookRequest(){
        System.out.println("Hello world");
        return  null;
    }
}
