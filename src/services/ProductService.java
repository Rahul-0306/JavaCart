package services;

import models.Product;
import java.util.List;
import java.util.function.Predicate;

public interface ProductService {
    List<Product> getAllProducts();
    void addProduct(Product product);
    void removeProduct(String productId);
    List<Product> filterProducts(Predicate<Product> predicate);
}
