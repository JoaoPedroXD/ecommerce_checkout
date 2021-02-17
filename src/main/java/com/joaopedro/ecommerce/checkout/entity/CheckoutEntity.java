package com.joaopedro.ecommerce.checkout.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Builder
@Data
@Entity
public class CheckoutEntity {
    //Atributos
    @Id
    @GeneratedValue //Indica que o DB gera o número para o campo
    private Long id;
    @Column
    private String code;
    @Column
    @Enumerated(value = EnumType.STRING)
    private Status status;

    //Situações do Checkout
    public enum Status {
        CREATED,
        APPROVED,
        REJECTED,
        FAILURE
    }
}
