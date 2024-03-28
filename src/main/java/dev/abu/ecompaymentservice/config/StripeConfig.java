package dev.abu.ecompaymentservice.config;

import com.stripe.StripeClient;
import com.stripe.exception.StripeException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Getter
public class StripeConfig {

    @Value("${stripe.key.id}")
    private String stripeKeyId;

    @Value("${stripe.key.secret}")
    private String stripeKeySecret;




}
