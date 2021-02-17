package com.joaopedro.ecommerce.checkout.resource.checkout;

import com.joaopedro.ecommerce.checkout.entity.CheckoutEntity;
import com.joaopedro.ecommerce.checkout.service.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/checkout")
@RequiredArgsConstructor
public class CheckoutResource {
    private final CheckoutService checkoutService;

    @PostMapping("/")
    //Inicia o Checkout
    public ResponseEntity<CheckoutResponse> create(CheckoutRequest checkoutRequest) {
        //Define e recupera uma Entidade de Checkout, caso o contrário, dispara uma exceção
        final CheckoutEntity checkoutEntity = checkoutService.create(checkoutRequest).orElseThrow();
        //Define a resposta do Checkout
        final CheckoutResponse checkoutResponse = CheckoutResponse.builder()
                .code(checkoutEntity.getCode()) //Informa o código
                .build();
        //Retorna a situação do Checkout
        return ResponseEntity.status(HttpStatus.CREATED).body(checkoutResponse);
    }
}
