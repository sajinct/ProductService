package com.sajinct.productservice.services;

import com.sajinct.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(long id);
    List<Product> getAllProducts();
    Product updateProduct(long id, Product product);
    Product addProduct(Product product);
    Product replaceProduct(long id, Product product);
    void deleteProduct(long id);
}
