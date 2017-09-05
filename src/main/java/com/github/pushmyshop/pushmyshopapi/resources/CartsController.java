package com.github.pushmyshop.pushmyshopapi.resources;


import com.github.pushmyshop.pushmyshopapi.models.Cart;
import com.github.pushmyshop.pushmyshopapi.models.Compagny;
import com.github.pushmyshop.pushmyshopapi.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/compagnies/{compagnyId}/carts")
public class CartsController {

    @Autowired
    protected Compagnies compagnies;
    @Autowired
    protected Carts carts;

    @PostMapping
    public Cart createFor(@PathVariable  long compagnyId){
        Compagny compagny = compagnies.findOne(compagnyId);
        Cart newCart = new Cart();
        newCart.setCompagny(compagny);
        newCart = carts.save(newCart);
        return newCart;
    }

    @PostMapping
    @RequestMapping("/{cartId}/product")
    public Cart addProduct(@PathVariable  String cartId, @RequestBody Product product){
        Cart cart = carts.findOne(UUID.fromString(cartId));
        cart.getProducts().add(product);
        cart = carts.save(cart);
        return cart;
    }
}


