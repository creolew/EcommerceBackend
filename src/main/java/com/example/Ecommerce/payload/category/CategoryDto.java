package com.example.Ecommerce.payload.category;

import com.example.Ecommerce.entity.Category;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CategoryDto {

    private Integer id;

    private String name;
    private String alias;

    private String image;

    private boolean enabled;

//    private Category parent;
//
//    private Set<Category> children = new HashSet<>();
}
