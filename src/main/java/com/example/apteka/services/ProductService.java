package com.example.apteka.services;

import com.example.apteka.model.Product;
import com.example.apteka.repositories.ProductRepository;
import com.example.apteka.warehouse.ProductRestClient;
import com.example.apteka.warehouse.ProductSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //klasa odpowiada za operacje związane z produktami
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductRestClient restClient;
    /**
     * wstrzyknięcie repozytoriów
     */
    @Autowired
    public ProductService(ProductRepository productRepository, ProductRestClient restClient) {
        this.productRepository = productRepository;
        this.restClient = restClient;
    }

    /**
     * tworzenie nowego produktu
     */
    public Product createNewProduct (Product product)
    {
        productRepository.save(product);
        return product;
    }
    /**
     * zwraca wszystkie produkty
     */
    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }
    /**
     * zwraca wszystkie produkty z konkretną nazwą
     */
    public List<Product> getAllByName (String name)
    {
        return productRepository.findAllByName(name);
    }
    /**
     * zwraca wszystkie produkty z konkretną kategorią
     */
    public List<Product> getAllByCategory(String category)
    {
        return productRepository.findAllByCategoryName(category);
    }
    /**
     * nie zaimplementowano, będzie zwracać produkty konkretnego producenta
     */
    public List<Product> getAllByProducer (String producer)
    {
        return productRepository.findAllByProducer(producer);
    }

    public List<ProductSource> getAllFromWarehouseByName(String name) {
        return restClient.readProductsByName(name);
    }

    public List<ProductSource> getAllFromWarehouse() {return restClient.readAllProducts();}
}
