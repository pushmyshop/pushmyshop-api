package com.github.pushmyshop.pushmyshopapi.config;

import com.github.pushmyshop.pushmyshopapi.models.Compagny;
import com.github.pushmyshop.pushmyshopapi.models.Customer;
import com.github.pushmyshop.pushmyshopapi.models.Product;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Customer.class, Compagny.class, Product.class);
    }
}