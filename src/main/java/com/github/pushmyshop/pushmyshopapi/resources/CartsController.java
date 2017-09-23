package com.github.pushmyshop.pushmyshopapi.resources;


import com.github.pushmyshop.pushmyshopapi.models.Cart;
import com.github.pushmyshop.pushmyshopapi.models.Compagny;
import com.github.pushmyshop.pushmyshopapi.models.Product;
import com.github.pushmyshop.pushmyshopapi.models.PushSubscription;
import lombok.extern.slf4j.Slf4j;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/compagnies/{compagnyId}/carts")
public class CartsController {

    private List<PushSubscription> activeSubscription = new ArrayList<>();

    @Autowired
    protected Compagnies compagnies;
    @Autowired
    protected Carts carts;
    @Autowired
    protected PushService pushService;

    private static String NOTIFICATION_TEMPLATE = "{\n" +
            " \"notification\": \n" +
            "   \n{\n" +
            "   \"title\" : \"TITLE\",\n" +
            "   \"body\" : \"MESSAGE\",\n" +
            "   \"icon\" : \"ICON\"\n" +
            " }}\n";

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
    public Cart checkoutCart(@PathVariable  String cartId, @RequestBody Cart cartToCheckout){
        Cart cart = carts.findOne(UUID.fromString(cartId));
        cart.getPickingInformation(cartToCheckout);
        cart.setState(Cart.State.VALIDATED);
        cart= carts.save(cart);
        sendNotification(cart.getCompagny().getSubscription()
                , NOTIFICATION_TEMPLATE
                        .replace("TITLE", cart.getCompagny().getName())
                        .replace("MESSAGE", "Nouvelle commande !")
                        .replace("ICON", cart.getCompagny().getLogo()).getBytes());
        return cart;
    }

    @PostMapping
    @RequestMapping("/{cartId}/confirm")
    public Cart confirmCart(@PathVariable  String cartId){
        Cart cart = carts.findOne(UUID.fromString(cartId));
        cart.setState(Cart.State.CONFIRMED);
        cart= carts.save(cart);
        sendNotification(cart.getSubscription(), NOTIFICATION_TEMPLATE
                .replace("TITLE", cart.getCompagny().getName())
                .replace("MESSAGE", "Votre commande a été confirmée")
                .replace("ICON", cart.getCompagny().getLogo()).getBytes());
        return cart;
    }

    @PostMapping
    @RequestMapping("/{cartId}/cancel")
    public Cart cancelCart(@PathVariable  String cartId){
        Cart cart = carts.findOne(UUID.fromString(cartId));
        cart.setState(Cart.State.CANCELED);
        cart= carts.save(cart);
        sendNotification(cart.getSubscription(), NOTIFICATION_TEMPLATE
                .replace("TITLE", cart.getCompagny().getName())
                .replace("MESSAGE", "Votre commande a été annulée")
                .replace("ICON", cart.getCompagny().getLogo()).getBytes());
        return cart;
    }

    @PostMapping
    @RequestMapping("/{cartId}/webpush")
    public String subscribe(@PathVariable  String cartId, @RequestBody PushSubscription subscription) {
        Cart cart = carts.findOne(UUID.fromString(cartId));
        cart.setSubscription(subscription);
        carts.save(cart);
        return "\"Web push subscribed\"";
    }

    @PostMapping
    @RequestMapping("/webpush")
    public String subscribe(@PathVariable  long compagnyId, @RequestBody PushSubscription subscription) {
        Compagny compagny = compagnies.findOne(compagnyId);
        compagny.setSubscription(subscription);
        compagnies.save(compagny);
        return "\"Web push subscribed\"";
    }

    private void sendNotification(PushSubscription sub, byte[] payload){
        if(sub !=null) {
            try {
                Notification notification = new Notification(
                        sub.getEndpoint(),
                        sub.getPublicKey(),
                        sub.getAuth(),
                        payload
                );
                // Send the notification
                pushService.send(notification);
            } catch (IOException | JoseException | ExecutionException
                    | GeneralSecurityException | InterruptedException e) {
                log.error("Could not Send notification :", e);
            }
        }
    }

}


