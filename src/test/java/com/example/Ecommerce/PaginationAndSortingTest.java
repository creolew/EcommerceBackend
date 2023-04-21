package com.example.Ecommerce;


import com.example.Ecommerce.entity.Category;
import com.example.Ecommerce.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class PaginationAndSortingTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void pagination(){
        int pageNo = 2;
        int pageSize = 5;

        Pageable pageable = PageRequest.of(pageNo,pageSize);

        Page<Category> page = categoryRepository.findAll(pageable);

        List<Category> categories = page.getContent();

        categories.forEach(p -> {
            System.out.println(p.getName());
        });

        //total pages
        int totalPages = page.getTotalPages();
        //total elements
        long totalElements = page.getTotalElements();
        //number of elements
        int numberOfElements = page.getNumberOfElements();
        //size
        int size = page.getSize();
        //last
        boolean isLast = page.isLast();
        //first
        boolean isFirst = page.isFirst();

        System.out.println("Total page: " + totalPages);
        System.out.println("Total elements: " + totalElements);
        System.out.println("Number of Elements: " + numberOfElements);
        System.out.println("Size: " + size);
        System.out.println("isFirst: " + isFirst);
        System.out.println("isLast: " + isLast);


    }

    @Test
    void sorting(){
        String sortBy = "alias";
        String sortDir = "desc";

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        List<Category> categories = categoryRepository.findAll(sort);

        categories.forEach(p -> {
            System.out.println(p.getName());
        });

    }

    @Test
    void paginationAndSortingTogether(){
        String sortBy = "alias";

        String sortDir = "desc";

        int pageNo = 0;

        int pageSize = 5;

        //Sort object
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        //Pageable object
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Category> page = categoryRepository.findAll(pageable);

        List<Category> categories = page.getContent();

        categories.forEach(p -> {
            System.out.println(p.getName());
        });

        //total pages
        int totalPages = page.getTotalPages();
        //total elements
        long totalElements = page.getTotalElements();
        //number of elements
        int numberOfElements = page.getNumberOfElements();
        //size
        int size = page.getSize();
        //last
        boolean isLast = page.isLast();
        //first
        boolean isFirst = page.isFirst();

        System.out.println("Total page: " + totalPages);
        System.out.println("Total elements: " + totalElements);
        System.out.println("Number of Elements: " + numberOfElements);
        System.out.println("Size: " + size);
        System.out.println("isFirst: " + isFirst);
        System.out.println("isLast: " + isLast);

    }


}
