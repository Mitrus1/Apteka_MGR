package com.example.apteka.services;

import com.example.apteka.exceptions.AppException;
import com.example.apteka.model.Product;
import com.example.apteka.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service("order")
@AllArgsConstructor
public class OrderQuantityService implements QuantityService {
    private final ProductRepository productRepository;
    private final OrderCart orderCart;

    /**
     * odpowiada za dodanie produktu do koszyka
     */
    @Override
    public void modifyQuantity(Long productId, int quantity)
    {
        Product product = productRepository.findById(productId).get();
        if (product.getAmount()>= quantity)
        {
            orderCart.add(product, quantity);
            product.setAmount(product.getAmount()-quantity);
            productRepository.save(product);
        }else {
            throw new AppException("Brak takiej ilości na magazynie");
        }
    }
}
