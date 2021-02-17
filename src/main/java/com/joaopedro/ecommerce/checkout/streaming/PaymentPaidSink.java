package com.joaopedro.ecommerce.checkout.streaming;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface PaymentPaidSink {
    //Tópico
    String INPUT = "payment-paid-input";

    @Input(INPUT)
    SubscribableChannel input();
}
