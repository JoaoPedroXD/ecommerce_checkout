package com.joaopedro.ecommerce.checkout.service;

import com.joaopedro.ecommerce.checkout.entity.CheckoutEntity;
import com.joaopedro.ecommerce.checkout.resource.checkout.CheckoutRequest;

import java.util.Optional;

//Interface que intermedia o processo de criação do Checkout
public interface CheckoutService {
    Optional<CheckoutEntity> create(CheckoutRequest checkoutRequest);
}
