package com.example.Ecommerce.payload.product;

import com.example.Ecommerce.entity.Category;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
public class ProductDto {

    private Integer id;

    private String name;

    private String description;

    private boolean enabled;

    private Date createdTime;

    private Date updatedTime;

    private Float cost;

    private String category;
}
