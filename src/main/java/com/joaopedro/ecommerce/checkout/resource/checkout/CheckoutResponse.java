package com.joaopedro.ecommerce.checkout.resource.checkout;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class CheckoutResponse implements Serializable {
    //Atributos
    private String code;
}
