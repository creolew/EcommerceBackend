package com.example.Ecommerce.repository;

import com.example.Ecommerce.entity.Role;
import com.example.Ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    boolean existsByEmail(String email);


}
