package com.example.apteka.controllers;

import com.example.apteka.model.Product;
import com.example.apteka.services.CategoryService;
import com.example.apteka.services.ProductService;
import com.example.apteka.services.QuantityService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller  //klasa odpowiada za widoki produktów
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final QuantityService quantityService;
    /**
     * wstrzyknięcie serwisów od produktów i kategorii
     */
    public ProductController(ProductService productService, CategoryService categoryService, @Qualifier("product") QuantityService quantityService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.quantityService = quantityService;
    }
    /**
     * wyświetla produkty z danej kategorii
     */
    @GetMapping("/products/{category}")
    public String getAllByCategory(Model model, @PathVariable String category, @RequestParam(required = false) String producer)
    {
        if (producer !=null)
        {
            model.addAttribute("products", productService.getAllByProducer(producer));
        }else
        {
            model.addAttribute("products", productService.getAllByCategory(category));
        }
        return "productList";
    }
    /**
     * wyświetla wszystkie produkty
     */
    @GetMapping("/products")
    public String getAllProducts(Model model)
    {
        model.addAttribute("products", productService.getAllProducts());
        return "productList";
    }
    /**
     * odpowiada za wyszukiwarke produktów
     */
    @PostMapping("/productsByName")
    public String getAllByName (Model model, @RequestParam (value = "productName") String productName)
    {
        if (productName != null)
        {
            model.addAttribute("productName", productName);
            model.addAttribute("products", productService.getAllByName(productName));
        }
        return "productList";
    }
    /**
     * odpowiada za widok dodania do bazy nowego produktu
     */
    @GetMapping("/addProduct")
    public String addProductPage (Model model)
    {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "addProduct";
    }
    /**
     * odpowiada za widok dodawania ilości produktu
     */
    @GetMapping("/addQuantity/{productId}")
    public String addQuantity (Model model, @PathVariable Long productId)
    {
        return "addQuantity";
    }
    /**
     * odpowiada za dodanie do bazy wiekszej ilości produktu
     */
    @PostMapping("/addQuantityAction/{productId}")
    public String addQuantityAction (Model model, @RequestParam int quantity, @PathVariable Long productId)
    {
        model.addAttribute("message", "Dodano "+quantity+ " sztuk/i produktu");
        quantityService.modifyQuantity(productId, quantity);
        return "addQuantity";
    }
    /**
     * odpowiada za dodanie do bazy nowego produktu
     */
    @PostMapping("/addProductAction")
    public String addProduct (@ModelAttribute Product product, Model model)
    {
        model.addAttribute("success", "Dodano nowy produkt");
        productService.createNewProduct(product);
        return "success";
    }

    @PostMapping("/warehouseByName")
    public String getProductsFromWarehouseAction(Model model, @RequestParam(value = "name") String name) {
        if (name != null) {
            model.addAttribute("name", name);
            model.addAttribute("productsSource", productService.getAllFromWarehouseByName(name));
        }
        return "warehouse";
    }

    @GetMapping("/warehouse")
    public String getProductsFromWarehouse(Model model) {
        model.addAttribute("productsSource", productService.getAllFromWarehouse());
        return "warehouse";
    }
}
