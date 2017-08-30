package com.github.pushmyshop.pushmyshopapi.handlers;

import com.github.pushmyshop.pushmyshopapi.models.Customer;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Slf4j
@RepositoryEventHandler(Customer.class)
public class CustomerEventHandler {

    @HandleAfterCreate
    public void handleAfterCreate(Customer customer) {
        log.info("[After Create] Create file for generate web app");
        
    }
}