package com.example.apteka.controllers;

import com.example.apteka.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //klasa odpowiada za widoki na stronie głównej
public class HomeController {
    private final CategoryService categoryService;

    /**
     * wstrzyknięcie serwisu od kategorii
     */
    @Autowired
    public HomeController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * odpowiada za widok strony głównej, wyświetla kategorie
     */
    @GetMapping("/")
    public String home(Model model)
    {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "index";
    }

    /**
     *
     * widok operacji zakończonej sukcesem
     */
    @GetMapping("/success")
    public String success ()
    {
        return "success";
    }
    /**
     *
     * widok operacji zakończonej błędem
     */
    @GetMapping("/fail")
    public String error ()
    {
        return "fail";
    }
}
