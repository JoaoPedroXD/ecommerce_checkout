package com.joaopedro.ecommerce.checkout.listener;

import com.joaopedro.ecommerce.checkout.entity.CheckoutEntity;
import com.joaopedro.ecommerce.checkout.event.PaymentCreatedEvent;
import com.joaopedro.ecommerce.checkout.repository.CheckoutRepository;
import com.joaopedro.ecommerce.checkout.streaming.PaymentPaidSink;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentPaid {
    private CheckoutRepository checkoutRepository;

    @StreamListener(PaymentPaidSink.INPUT)
    //Verifica, se o pagamento foi encontrado e foi aprovado, seta como aprovado e salva no banco de dados
    public void handler(PaymentCreatedEvent event) {
        final CheckoutEntity checkoutEntity = checkoutRepository
                .findByCode(event.getCheckoutCode().toString())
                .orElseThrow();
        checkoutEntity.setStatus(CheckoutEntity.Status.APPROVED);
        checkoutRepository.save(checkoutEntity);
    }
}
