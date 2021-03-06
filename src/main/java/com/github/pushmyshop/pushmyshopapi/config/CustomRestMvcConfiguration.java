package com.github.pushmyshop.pushmyshopapi.config;

import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Utils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

@Configuration
class CustomRestMvcConfiguration {

    @Value("${web.push.vapid.public-key}")
    private String vapidPublicKey;
    @Value("${web.push.vapid.private-key}")
    private String vapidPrivateKey;

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {

        return new RepositoryRestConfigurerAdapter() {

            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
                config.setBasePath("/api");
            }
        };
    }

    @Bean
    public PushService pushService() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {

        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }

        PushService pushService = new PushService();
        pushService.setPrivateKey(Utils.loadPrivateKey(this.vapidPrivateKey));
        pushService.setPublicKey(Utils.loadPublicKey(this.vapidPublicKey));
        return pushService;
    }
}
