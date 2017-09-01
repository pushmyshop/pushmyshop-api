package com.github.pushmyshop.pushmyshopapi.handlers;

import com.github.pushmyshop.pushmyshopapi.models.Compagny;
import com.github.pushmyshop.pushmyshopapi.models.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterLinkSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@RepositoryEventHandler(Compagny.class)
public class CompagnyEventHandler {

    @HandleAfterLinkSave
    public void handleAfterCreate(Compagny compagny) {
        log.info("[After Create] Create file for generate web app");
        ProcessBuilder process = new ProcessBuilder("yo", "pushmyshop", compagny.getName(), "/tmp/");
        try {
            process.start();
        } catch (IOException exception) {
            log.error(exception.getMessage(), exception);
        }
    }
}