package com.github.pushmyshop.pushmyshopapi.resources;

import com.github.pushmyshop.pushmyshopapi.models.Cart;
import com.github.pushmyshop.pushmyshopapi.models.Compagny;
import com.github.pushmyshop.pushmyshopapi.models.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartsControllerTest {

    @Mock
    Compagnies compagnies;
    @Mock
    Carts carts;

    CartsController controller;

    @Before
    public void init(){
        controller = new CartsController();
        controller.carts = this.carts;
        controller.compagnies = this.compagnies;
    }

    @Test
    public void create_cart_for_company_and_return() {
        Compagny compagny = new Compagny();
        Cart expectedCart = new Cart();
        when(compagnies.findOne(123l)).thenReturn(compagny);
        when(carts.save(any(Cart.class))).thenReturn(expectedCart);

        Cart actualCart = controller.createFor(123l);

        assertThat(actualCart).isEqualTo(expectedCart);
    }

    @Test
    public void add_product_to_cart() {
        String cartId = "37a5e98d-5ea1-4baf-a765-f361f5025d17";
        Product product = new Product();
        Cart expectedCart = new Cart();
        List<Product> listOfProduct = expectedCart.getProducts();
        when(carts.findOne(UUID.fromString(cartId))).thenReturn(expectedCart);
        when(carts.save(expectedCart)).thenReturn(expectedCart);

        Cart actualCart = controller.addProduct(cartId, product);

        assertThat(actualCart).isEqualTo(expectedCart);
        assertThat(listOfProduct).contains(product);
    }
}