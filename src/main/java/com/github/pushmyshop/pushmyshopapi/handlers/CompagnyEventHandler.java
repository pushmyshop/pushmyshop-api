package com.github.pushmyshop.pushmyshopapi.handlers;

import com.github.pushmyshop.pushmyshopapi.models.Compagny;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

@Component
@Slf4j
@RepositoryEventHandler(Compagny.class)
public class CompagnyEventHandler {

    @HandleAfterSave()
    public void handleAfterSave(Compagny compagny) {
        log.info("[After Save] Create file for generate web app");
        ProcessBuilder processBuilder = new ProcessBuilder("yo", "pushmyshop", "/tmp/pushmyshop/"
                , String.format("%s", compagny.getId())
                , String.format("%s", compagny.getLogo())
                , String.format("%s", compagny.getImage())
                , String.format("%s", compagny.getName()));
        try {
            processBuilder.redirectErrorStream(true);
            processBuilder.directory(new File("/tmp/"));

            Process process = processBuilder.start();
            StreamGobbler streamGobbler =
                    new StreamGobbler(process.getInputStream(), System.out::println);
            Executors.newSingleThreadExecutor().submit(streamGobbler);
            log.info("generated web site");
        } catch (IOException exception) {
            log.error(exception.getMessage(), exception);
        }
    }
    private static class StreamGobbler implements Runnable {
        private InputStream inputStream;
        private Consumer<String> consumer;

        public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
            this.inputStream = inputStream;
            this.consumer = consumer;
        }

        @Override
        public void run() {
            new BufferedReader(new InputStreamReader(inputStream)).lines()
                    .forEach(consumer);
        }
    }
}