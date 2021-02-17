package com.joaopedro.ecommerce.checkout.service;

import com.joaopedro.ecommerce.checkout.entity.CheckoutEntity;
import com.joaopedro.ecommerce.checkout.event.CheckoutCreatedEvent;
import com.joaopedro.ecommerce.checkout.repository.CheckoutRepository;
import com.joaopedro.ecommerce.checkout.resource.checkout.CheckoutRequest;
import com.joaopedro.ecommerce.checkout.streaming.CheckoutCreatedSource;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor //O lombok cria um construtor para os atributos finais, isso em tempo de execução
public class CheckoutServiceImpl implements CheckoutService {
    private final CheckoutRepository checkoutRepository;
    private final CheckoutCreatedSource checkoutCreatedSource;

    @Override
    public Optional<CheckoutEntity> create(CheckoutRequest checkoutRequest) {
        //Cria a entidade usando o método Builder do Lombok
        final CheckoutEntity checkoutEntity = CheckoutEntity.builder()
                .code(UUID.randomUUID().toString()) //Gera um código de ID aleatório para o checkout
                .status(CheckoutEntity.Status.APPROVED)
                .build(); //Realiza o Build
        final CheckoutEntity entity = checkoutRepository.save(checkoutEntity); //Salva o checkout repository
        final CheckoutCreatedEvent checkoutCreatedEvent = CheckoutCreatedEvent.newBuilder() // Cria o evento de checkout
                .setCheckoutCode(entity.getStatus().name()) //Pega o Status da transação
                .build();
        //Envia a mensagem para o Kafka ou qualquer outro serviço de mensagens
        checkoutCreatedSource.output().send(MessageBuilder.withPayload(checkoutCreatedEvent).build());
        //Retorna a entidade de checkout
        return Optional.of(entity);
    }
}
