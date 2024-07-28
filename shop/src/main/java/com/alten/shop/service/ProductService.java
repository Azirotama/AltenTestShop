package com.alten.shop.service;

import com.alten.shop.model.Product;
import com.alten.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product getProductByCode(String code) {
        return productRepository.findByCode(code);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> getProductsByStatus(String status) {
        return productRepository.findByStatus(status);
    }

    public List<Product> getProductsByPriceGreaterThan(double price) {
        return productRepository.findByPriceGreaterThan(price);
    }

    public List<Product> getProductsByPriceLessThan(double price) {
        return productRepository.findByPriceLessThan(price);
    }

    public List<Product> getProductsByPriceBetween(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> getProductsByNameContaining(String keyword) {
        return productRepository.findByNameContaining(keyword);
    }

    public List<Product> getProductsByQuantityGreaterThan(int quantity) {
        return productRepository.findByQuantityGreaterThan(quantity);
    }

    public List<Product> getProductsByRatingGreaterThan(int rating) {
        return productRepository.findByRatingGreaterThan(rating);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProduct(int id, Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            productRepository.save(product);
        }
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}
