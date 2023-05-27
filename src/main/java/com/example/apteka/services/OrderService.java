package com.example.apteka.services;

import com.example.apteka.model.Assign;
import com.example.apteka.model.Product;
import com.example.apteka.repositories.OrderRepository;
import com.example.apteka.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("orderService") //klasa odpowiada za operacje związane z zamówieniami
public class OrderService {
    private final ProductRepository productRepository;
    private final OrderCart orderCart;
    private final OrderRepository orderRepository;

    /**
     * wstrzyknięcie repozytoriów
     */
    @Autowired
    public OrderService(ProductRepository productRepository, OrderRepository orderRepository, OrderCart orderCart) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderCart = orderCart;
    }

    /**
     * zwraca aktualny koszyk
     */
    public Assign getCurrentOrder ()
    {
        return orderCart.getAssign();
    }

    /**
     * zlicza sumę w koszyku
     */
    public double getCurrentSum ()
    {
        List<Product> products = orderCart.getAssign().getProducts();

        return products.stream()
                .map(p -> p.getPrice() * p.getAmount())
                .reduce(0.0, Double::sum);
    }
    /**
     * usuwa produkty z koszyka
     */
    public void removeProductFromCart(Long productId)
    {
        Product product = productRepository.findById(productId).get();
        Product p = orderCart.getAssign().getProducts()
                .stream()
                .filter(prod -> prod.getName().equals(product.getName()))
                .findFirst().orElse(product);
        product.setAmount((product.getAmount())+p.getAmount());
        orderCart.getAssign().getProducts().remove(p);
        productRepository.save(product);
    }

    /**
     * realizuje koszyk
     */
    public void finishOrder ()
    {
        Assign assign = orderCart.getAssign();
        assign.setSumPrice(getCurrentSum());
        assign.setFinishTime(LocalDateTime.now());
        orderRepository.save(assign);
        orderCart.clear();
    }

    /**
     * zwraca listę zakonczonych zamówień
     */
    public List<Assign> getEndedOrders()
    {
        return orderRepository.findAll();
    }
}
