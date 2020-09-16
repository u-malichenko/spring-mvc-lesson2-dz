package ru.malichenko.springMVC.lesson2.dz.repositories;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;
import ru.malichenko.springMVC.lesson2.dz.model.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products = new ArrayList<>();
    private static Long count = 5L;

    @PostConstruct
    private void init() {
        products = new ArrayList<>(Arrays.asList(
                new Product(1L, "Bread", 25),
                new Product(2L, "Milk", 90),
                new Product(3L, "Cheese", 525),
                new Product(4L, "Chocolate", 124),
                new Product(5L, "Water", 15)
        ));
    }

    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(products);
    }

    public Product findProductById(Long id) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        throw new ResourceNotFoundException("Product not found");
    }

    public void save(Product product) {
        product.setId(++count);
        products.add(product);
    }

    public void deleteById(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }


}
