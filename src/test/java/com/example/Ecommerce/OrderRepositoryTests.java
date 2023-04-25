package com.example.Ecommerce;


import com.example.Ecommerce.entity.Order;
import com.example.Ecommerce.entity.OrderDetail;
import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.entity.User;
import com.example.Ecommerce.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //su dung csdl that de test
@Rollback(false)
public class OrderRepositoryTests {

    @Autowired
    private OrderRepository repo;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void testAddNewOrderWithSingleProduct(){
        User customer = entityManager.find(User.class,5);
        Product product = entityManager.find(Product.class, 1);

        Order mainOrder = new Order();
        mainOrder.setCustomer(customer);
        mainOrder.setFirstName(customer.getFirstName());
        mainOrder.setLastName(customer.getLastName());
        mainOrder.setProductCost(product.getCost());
        mainOrder.setUsername(customer.getUsername());
        mainOrder.setOrderTime(new Date());

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProduct(product);
        orderDetail.setOrder(mainOrder);
        orderDetail.setProductCost(product.getCost());
        orderDetail.setQuantity(1);

        mainOrder.getOrderDetails().add(orderDetail);

        Order savedOrder = repo.save(mainOrder);

        assertThat(savedOrder.getId()).isGreaterThan(0);



    }

    @Test
    public void testAddNewOrderWithMultipleProducts(){
        User customer = entityManager.find(User.class,4);
        Product product1 = entityManager.find(Product.class, 2);
        Product product2 = entityManager.find(Product.class, 3);

        Order mainOrder = new Order();
        mainOrder.setCustomer(customer);
        mainOrder.setFirstName(customer.getFirstName());
        mainOrder.setLastName(customer.getLastName());
        //mainOrder.setProductCost(product.getCost());
        mainOrder.setUsername(customer.getUsername());
        mainOrder.setOrderTime(new Date());

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProduct(product1);
        orderDetail1.setOrder(mainOrder);
        orderDetail1.setProductCost(product1.getCost());
        orderDetail1.setQuantity(1);



        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProduct(product2);
        orderDetail2.setOrder(mainOrder);
        orderDetail2.setProductCost(product2.getCost());
        orderDetail2.setQuantity(2);

        mainOrder.getOrderDetails().add(orderDetail1);
        mainOrder.getOrderDetails().add(orderDetail2);
        mainOrder.setProductCost(product2.getCost() * orderDetail2.getQuantity() + product1.getCost() * orderDetail1.getQuantity());

        Order savedOrder = repo.save(mainOrder);

        assertThat(savedOrder.getId()).isGreaterThan(0);

    }

    @Test
    public void testListOrders(){
       Iterable<Order> orders = repo.findAll();
       assertThat(orders).hasSizeGreaterThan(0);

       orders.forEach(System.out::println);

    }

    @Test
    public void testGetOrder(){
        Integer orderId = 5;

        Order order = repo.findById(orderId).get();

        System.out.println(order);
        assertThat(order).isNotNull();

    }


}
