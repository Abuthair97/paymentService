package dev.abu.ecompaymentservice.service;

import org.springframework.stereotype.Service;

@Service
public class paymentSelectionStratergyImpl implements paymentSelectionStratergy{

    @Override
    public int plainPaymentSelection() {
       int random = (int)Math.floor(Math.random()*10)+1;
       if(random >= 1 && random <= 7){
           return 1;
       }
       else {
           return 0;
       }
    }

    }
