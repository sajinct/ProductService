package com.sajinct.productservice.controllers;

import com.sajinct.productservice.models.Product;
import com.sajinct.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    ProductService productService;

    ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") long id){
        return productService.getProductById(id);
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return new ArrayList<>();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductByID(@PathVariable("id") long id){

    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") long id, @RequestBody Product product){
        return new Product();
    }

    @PatchMapping("/{id}")
    public Product patchProduct(@PathVariable("id") long id, @RequestBody Product product){
        return new Product();
    }
}
