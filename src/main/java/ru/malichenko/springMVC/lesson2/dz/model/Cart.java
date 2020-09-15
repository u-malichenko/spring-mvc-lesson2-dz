package ru.malichenko.springMVC.lesson2.dz.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.malichenko.springMVC.lesson2.dz.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
//@Scope("prototype")
public class Cart {
    private List<Product> products = new ArrayList<>();
    private ProductRepository productRepository;

    public Cart() {
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(products);
    }

    public void addProductById(Long id) {
        products.add(productRepository.findProductById(id));
    }

    public void deleteProductById(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }

    @Override
    public String toString() {
        return "Cart{" +
                "products=" + products +
                '}';
    }
}
