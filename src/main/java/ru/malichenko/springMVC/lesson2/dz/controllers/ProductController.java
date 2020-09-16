package ru.malichenko.springMVC.lesson2.dz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.malichenko.springMVC.lesson2.dz.model.Product;
import ru.malichenko.springMVC.lesson2.dz.services.ProductService;

import java.util.List;

@Controller
@RequestMapping()
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public String getAllProducts(Model model) {
        List<Product> list = productService.getAllProduct();
        model.addAttribute("products", list);
        return "all";
    }


    @PostMapping("/add_product")
    public String addNewProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/all";
    }

    @GetMapping("/delete_product/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        //todo добавить удаление товаров из корзин, если их удалили из каталога
        return "redirect:/all";
    }

}
