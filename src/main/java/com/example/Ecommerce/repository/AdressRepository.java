package com.example.Ecommerce.repository;

import com.example.Ecommerce.entity.Adress;
import com.example.Ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdressRepository extends JpaRepository<Adress,Integer> {
    Adress findByCustomer(User customer);
}
