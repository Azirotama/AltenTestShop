package com.alten.shop;

import com.alten.shop.model.Product;
import com.alten.shop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ShopApplicationTests {

    @Autowired
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService.getAllProducts().clear();
        productService.getAllProducts().add(new Product(1, "P001", "Product 1", "Description 1", 100.0, 10, "INSTOCK", "Category 1", "image1.jpg", 4));
        productService.getAllProducts().add(new Product(2, "P002", "Product 2", "Description 2", 150.0, 5, "OUTOFSTOCK", "Category 2", "image2.jpg", 5));
    }

    @Test
    void contextLoads() {
    }

    @Test
    void testGetAllProductIds() {
        List<Product> products = productService.getAllProducts();
        products.forEach(product -> System.out.println("Product ID: " + product.getId()));
        assertEquals(2, products.size());
        assertEquals(1, products.get(0).getId());
        assertEquals(2, products.get(1).getId());
    }

    @Test
    void testGetAllProducts() {
        List<Product> products = productService.getAllProducts();
        assertNotNull(products);
        assertEquals(2, products.size());
    }

    @Test
    void testGetProductById() {
        Product product = productService.getAllProducts().stream().filter(p -> p.getId() == 1).findFirst().orElse(null);
        assertNotNull(product);
        assertEquals("Product 1", product.getName());
    }

    @Test
    void testAddProduct() {
        Product newProduct = new Product(3, "P003", "Product 3", "Description 3", 200.0, 7, "INSTOCK", "Category 3", "image3.jpg", 4);
        productService.getAllProducts().add(newProduct);
        assertEquals(3, productService.getAllProducts().size());
        assertEquals("Product 3", productService.getAllProducts().get(2).getName());
    }

    @Test
    void testRemoveProduct() {
        productService.getAllProducts().removeIf(p -> p.getId() == 1);
        assertEquals(1, productService.getAllProducts().size());
        Product remainingProduct = productService.getAllProducts().get(0);
        assertEquals(2, remainingProduct.getId());
    }

}
