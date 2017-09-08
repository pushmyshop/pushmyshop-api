package com.github.pushmyshop.pushmyshopapi.resources;


import com.github.pushmyshop.pushmyshopapi.models.Cart;
import com.github.pushmyshop.pushmyshopapi.models.Compagny;
import com.github.pushmyshop.pushmyshopapi.models.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface Carts extends CrudRepository<Cart, UUID> {

    List<Cart> findByCompagny(Compagny compagny);
}


