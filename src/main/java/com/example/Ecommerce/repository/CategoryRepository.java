package com.example.Ecommerce.repository;

import com.example.Ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);

    @Query(value = "select * from categories cate where cate.name like concat ('%' ,:query, '%') "
            + " or cate.alias like concat ('%' ,:query, '%') ", nativeQuery = true)
    List<Category> searchCategoryByName(String query);

}
