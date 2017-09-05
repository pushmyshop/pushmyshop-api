package com.github.pushmyshop.pushmyshopapi.resources;


import com.github.pushmyshop.pushmyshopapi.models.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface Carts extends CrudRepository<Cart, UUID> {

}


