package com.example.Ecommerce;


import com.example.Ecommerce.entity.Category;
import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.entity.User;
import com.example.Ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //su dung csdl that de test
@Rollback(false)
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateProduct(){
        Category category = entityManager.find(Category.class, 5);

        Product product = new Product();
        product.setName("RAM Laptop Kingston");
        product.setDescription(" Kingston Sodimm 1.2V 16GB 3200MHz CL22 ");
        product.setCategory(category);
        product.setCost(40);
        product.setCreatedTime(new Date());
        product.setUpdatedTime(new Date());

        Product savedProduct = productRepository.save(product);
        assertThat(savedProduct.getId()).isGreaterThan(0);

    }

    @Test
    public void testListAllUsers(){
        Iterable<Product> listProducts = productRepository.findAll();
        listProducts.forEach(product -> System.out.println(product)) ;

    }

    @Test
    public void testGetProductById(){
        Product product = productRepository.findById(1).get();
        System.out.println(product);
        assertThat(product).isNotNull();
    }

    @Test
    public void testUpdateProduct(){
        Integer id = 1;
        Product product = productRepository.findById(id).get();
        product.setEnabled(true);
        product.setCost(150);

        productRepository.save(product);

    }


}
