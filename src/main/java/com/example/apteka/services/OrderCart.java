package com.example.apteka.services;

import com.example.apteka.model.Assign;
import com.example.apteka.model.Product;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component   //klasa odpowiada za koszyk
@SessionScope  // adnotacja oznacza, że obiekty klasy są trzymane w pamięci sesji użytkownika
@Getter
public class OrderCart {
    private Assign assign;

    public OrderCart ()
    {
        clear();
    }

    /**
     * dodanie produktu i jego ilości do koszyka
     */
    void add(Product product, int quantity)
    {
        Product cartProduct = mapProductToCart(product);
        cartProduct.setAmount(quantity);
        assign.getProducts().add(cartProduct);
    }

    /**
     *
     * mapowanie produktu z bazy danych do koszyka
     */
    private Product mapProductToCart(Product product)
    {
        Product cartProduct = new Product();
        cartProduct.setId(product.getId());
        cartProduct.setProducer(product.getProducer());
        cartProduct.setPrice(product.getPrice());
        cartProduct.setName(product.getName());
        return cartProduct;
    }

    /**
     * czyszczenie koszyka po realizacji
     */

    void clear()
    {
        assign = new Assign();
    }
}
