package com.example.Ecommerce;

import com.example.Ecommerce.entity.Category;
import com.example.Ecommerce.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.assertj.core.api.InstanceOfAssertFactories.list;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //su dung csdl that de test
@Rollback(false)
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository repo;

    @Test
    public void testCreateRootCategory(){
        Category category = new Category("Electronics");

        Category savedCategory= repo.save(category);

        assertThat(savedCategory.getId()).isGreaterThan(0);

    }

    @Test
    public void testCreateSubCategory(){
        Category parent = new Category(7);
        Category subCat = new Category("iPhone", parent);


        Category savedCategory = repo.save(subCat);
        assertThat(savedCategory.getId()).isGreaterThan(0);

    }

    @Test
    public void testCategory(){
        Category category = repo.findById(2).get();

        System.out.println(category.getName());

         Set<Category> children= category.getChildren();

         for(Category subCat : children){
             System.out.println(subCat.getName());

         }

        assertThat(children.size()).isGreaterThan(0);


    }

    @Test
    public void testPrintHierarchicalCategories(){
        Iterable<Category> categories = repo.findAll();

        for(Category category : categories){
            if(category.getParent() == null){
                System.out.println(category.getName());

                Set<Category> children = category.getChildren();

                for(Category subCategory : children){
                    System.out.println("--" + subCategory.getName());

                    Set<Category> grandson = subCategory.getChildren();

                    if(grandson != null){
                        grandson.forEach(k -> System.out.println("++++" + k.getName()));
                    }

                }
            }
            System.out.println();
        }
    }


}
