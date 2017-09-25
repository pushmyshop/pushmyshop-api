package com.github.pushmyshop.pushmyshopapi.handlers;

import com.github.pushmyshop.pushmyshopapi.models.Compagny;
import com.github.pushmyshop.pushmyshopapi.models.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterLinkSave;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@RepositoryEventHandler(Compagny.class)
public class CompagnyEventHandler {

    @HandleAfterSave()
    public void handleAfterSave(Compagny compagny) {
        log.info("[After Save] Create file for generate web app");
        ProcessBuilder process = new ProcessBuilder("yo", "pushmyshop","/tmp/pushmyshop/",String.format("%s",compagny.getId()), String.format("%s",compagny.getName()));
        try {
            process.start();
              } catch (IOException exception) {
          log.error(exception.getMessage(), exception);
        }
    }
}