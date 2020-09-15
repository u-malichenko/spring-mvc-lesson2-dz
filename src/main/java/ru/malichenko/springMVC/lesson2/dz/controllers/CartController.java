package ru.malichenko.springMVC.lesson2.dz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.malichenko.springMVC.lesson2.dz.model.Cart;
import ru.malichenko.springMVC.lesson2.dz.model.Product;

import java.util.List;
@Controller
@RequestMapping("/cart")
public class CartController {

    private Cart cart;

    @Autowired
    public CartController(Cart cart) {
        this.cart = cart;
    }

    @GetMapping("/all")
    public String getAllProducts(Model cmodel) {
        List<Product> list = cart.getAllProducts();
        cmodel.addAttribute("products", list);
        return "cart";
    }


    @GetMapping("/add_product/{id}")
    public String addProduct(@PathVariable Long id) {
        cart.addProductById(id);
        return "redirect:/all";
    }

    @GetMapping("/delete_product/{id}")
    public String deleteProduct(@PathVariable Long id, Model cmodel) {
        cart.deleteProductById(id);
        List<Product> list = cart.getAllProducts();
        cmodel.addAttribute("products", list);
        return "cart";
    }
}
