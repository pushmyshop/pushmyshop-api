package com.github.pushmyshop.pushmyshopapi.handlers;

import com.github.pushmyshop.pushmyshopapi.models.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RepositoryEventHandler(Customer.class)
public class CustomerEventHandler {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @HandleBeforeCreate
    public void handleBeforeCreate(Customer customer) {
        log.info("[Before Create customer] Crypt password");
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
    }
}