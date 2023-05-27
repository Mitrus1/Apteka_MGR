package com.example.apteka.services;

import com.example.apteka.model.Product;
import com.example.apteka.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service("product")
@AllArgsConstructor
public class ProductQuantityService implements QuantityService {

    private final ProductRepository productRepository;

    @Override
    public void modifyQuantity(Long productId, int quantity)
    {
        Optional<Product> product = productRepository.findById(productId);
        product.ifPresent(p -> {
            p.setAmount(p.getAmount()+quantity);
            productRepository.save(p);
        });
    }
}
