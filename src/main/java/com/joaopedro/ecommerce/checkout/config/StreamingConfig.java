package com.joaopedro.ecommerce.checkout.config;

import com.joaopedro.ecommerce.checkout.streaming.CheckoutCreatedSource;
import com.joaopedro.ecommerce.checkout.streaming.PaymentPaidSink;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
//Passa qual a interface
@EnableBinding(value = {
        CheckoutCreatedSource.class,
        PaymentPaidSink.class
})
public class StreamingConfig {
}
