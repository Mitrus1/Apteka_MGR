package com.example.apteka.controllers;

import com.example.apteka.services.OrderService;
import com.example.apteka.services.QuantityService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller //klasa odpowiada za widoki zamówień
public class OrderController {
    private final OrderService orderService;
    private final QuantityService quantityService;

    /**
     * wstrzyknięcie serwisów od ogłoszeń
     */
    public OrderController(OrderService orderService, @Qualifier("order") QuantityService quantityService) {
        this.orderService = orderService;
        this.quantityService = quantityService;
    }

    /**
     * odpowiada za dodanie produktu do koszyka
     */
    @PostMapping("/addProductToCart/{productId}")
    public String addProductToCart(Model model, @PathVariable Long productId, @RequestParam int quantity)
    {
        if (quantity<=0) {
            model.addAttribute("error", "Musisz wybrać przynajmniej jedną sztukę");
            return "fail";
        }else {
            quantityService.modifyQuantity(productId, quantity);
            model.addAttribute("success", "Dodano do koszyka");
            return "success";
        }
    }

    /**
     * widok koszyka
     */
    @GetMapping("/cart")
    public String getCart (Model model)
    {
        if (orderService.getCurrentOrder().getProducts().size()>0)
        {
            model.addAttribute("emptyCart", "emptyCart");
        }
        model.addAttribute("products", orderService.getCurrentOrder().getProducts());
        model.addAttribute("sum", orderService.getCurrentSum());
        return "cart";
    }
    /**
     * lista historycznych zamówień
     */
    @GetMapping("/archive")
    public String getEndedOrders(Model model)
    {
        model.addAttribute("orders", orderService.getEndedOrders());
        return "archive";
    }
    /**
     * odpowiada za usunięcie produktu z koszyka
     */
    @PostMapping("/removeProduct/{productId}")
    public String removeProduct (Model model, @PathVariable Long productId)
    {
        orderService.removeProductFromCart(productId);
        model.addAttribute("success", "Usunięto produkt");
        return "success";
    }

    /**
     * odpowiada za usunięcie produktu z koszyka
     */
    @PostMapping("/finishOrder")
    public String finishOrder(Model model)
    {
        orderService.finishOrder();
        model.addAttribute("success", "Zakończono zamówienie");
        return "success";
    }

}
