package com.example.Ecommerce.payload.category;

import com.example.Ecommerce.entity.Category;
import lombok.Data;

@Data
public class SubCategoryDto extends CategoryDto {

    private String parentCategory;


}
