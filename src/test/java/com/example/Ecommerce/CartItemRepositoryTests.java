package com.example.Ecommerce;


import com.example.Ecommerce.controller.customer.CustomerController;
import com.example.Ecommerce.entity.CartItem;
import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.entity.User;
import com.example.Ecommerce.repository.CartItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //su dung csdl that de test
@Rollback(false)
public class CartItemRepositoryTests {


    @Autowired
    private CartItemRepository repo;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void testSaveItem(){

        Integer customerId = 5;

        Integer productId = 1;

        User customer = entityManager.find(User.class, customerId);
        Product product = entityManager.find(Product.class, productId);

        CartItem newItem = new CartItem();
        newItem.setCustomer(customer);
        newItem.setProduct(product);
        newItem.setQuantity(1);

        CartItem  savedItem = repo.save(newItem);

        assertThat(savedItem.getId()).isGreaterThan(0);



    }

    @Test
    public void testSave2Items(){

        Integer customerId = 4;

        Integer productId1 = 3;
        Integer productId2 = 4;


        User customer = entityManager.find(User.class, customerId);

        Product product1 = entityManager.find(Product.class, productId1);
        Product product2 = entityManager.find(Product.class, productId2);


        CartItem newItem1 = new CartItem();
        newItem1.setCustomer(customer);
        newItem1.setProduct(product1);
        newItem1.setQuantity(1);

//        CartItem newItem2 = new CartItem();
//        newItem1.setCustomer(customer);
//        newItem1.setProduct(product2);
//        newItem1.setQuantity(3);
//
//        List<CartItem> cartItemList = new ArrayList<>();
//        cartItemList.add(newItem1);
//        cartItemList.add(newItem2);
//
//       repo.saveAll(cartItemList);

        repo.save(newItem1);

    }

    @Test
    public void testFindByCustomer(){
        Integer customerId = 4;
        User customer = entityManager.find(User.class, customerId);
        List<CartItem> listItems = repo.findByCustomer(customer);

        for(CartItem item : listItems){
            System.out.println(item);
        }

        assertThat(listItems.size()).isGreaterThan(0);

    }

    @Test
    public void testFindByCustomerAndProduct(){
        Integer customerId = 5;
        Integer productId = 1;

        User customer = entityManager.find(User.class, customerId);
        Product product = entityManager.find(Product.class, productId);


        CartItem item= repo.findByCustomerAndProduct(customer,product);
        assertThat(item).isNotNull();

        System.out.println(item);


    }

    @Test
    public void testUpdateQuantity(){
        Integer customerId = 5;
        Integer productId = 1;
        Integer quantity = 7;

        User customer = entityManager.find(User.class, customerId);
        Product product = entityManager.find(Product.class, productId);

        CartItem item= repo.findByCustomerAndProduct(customer,product);

        item.setQuantity(quantity);

        repo.save(item);

        assertThat(item.getQuantity()).isEqualTo(7);



    }

}
