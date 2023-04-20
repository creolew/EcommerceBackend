package com.example.Ecommerce.service;

import com.example.Ecommerce.entity.Category;
import com.example.Ecommerce.payload.category.CategoryDto;
import com.example.Ecommerce.payload.category.SubCategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> listAllCategories();

    List<CategoryDto> listSubCategories(String superCategory);

    String createSubCategory(SubCategoryDto categoryDto);

    CategoryDto updateCategory(Integer id, CategoryDto categoryDto);

    void deleteCategory(Integer id);

    CategoryDto listCategoryById(Integer id);
}
