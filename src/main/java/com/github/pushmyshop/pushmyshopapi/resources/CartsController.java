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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/compagnies/{compagnyId}/carts")
public class CartsController {

    @Autowired
    protected Compagnies compagnies;
    @Autowired
    protected Carts carts;

    @GetMapping
    public List<Cart> getAllFor(@PathVariable  long compagnyId){
        Compagny compagny = compagnies.findOne(compagnyId);
        return carts.findByCompagny(compagny);
    }

    @PostMapping
    public Cart createFor(@PathVariable  long compagnyId){
        Compagny compagny = compagnies.findOne(compagnyId);
        Cart newCart = new Cart();
        newCart.setCompagny(compagny);
        return carts.save(newCart);
    }

    @PostMapping
    @RequestMapping("/{cartId}/product")
    public Cart addProduct(@PathVariable  String cartId, @RequestBody Product product){
        Cart cart = carts.findOne(UUID.fromString(cartId));
        cart.getProducts().add(product);
        return carts.save(cart);
    }

    @PostMapping
    @RequestMapping("/{cartId}/product/{productId}")
    public Cart deleteProduct(@PathVariable  String cartId, @RequestBody Product product){
        Cart cart = carts.findOne(UUID.fromString(cartId));
        cart.getProducts().remove(product);
        return carts.save(cart);
    }

    @PostMapping
    @RequestMapping("/{cartId}/checkout")
    public Cart deleteProduct(@PathVariable  String cartId, @RequestBody Cart cartToCheckout){
        Cart cart = carts.findOne(UUID.fromString(cartId));
        cart.getPickingInformation(cartToCheckout);
        cart.setState(Cart.State.VALIDATED);
        return carts.save(cart);
    }
}


