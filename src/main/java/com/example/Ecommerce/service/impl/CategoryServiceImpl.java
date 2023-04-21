package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.entity.Category;
import com.example.Ecommerce.entity.User;
import com.example.Ecommerce.exception.ResourceNotFoundException;
import com.example.Ecommerce.payload.category.CategoryDto;
import com.example.Ecommerce.payload.category.SubCategoryDto;
import com.example.Ecommerce.repository.CategoryRepository;
import com.example.Ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> listAllCategories() {
        Iterable<Category> categories = categoryRepository.findAll();

        List<CategoryDto> listCategoryDto = new ArrayList<>();

        for(Category cat: categories){
            listCategoryDto.add(mapToDto(cat));
        }

        return listCategoryDto;


    }

    @Override
    public List<CategoryDto> listSubCategories(String superCategory) {
        Category category = categoryRepository.findByName(superCategory);
        if(category == null){
            throw new ResourceNotFoundException("Category", superCategory);
        }
        Set<Category> subCategories = category.getChildren();
        List<CategoryDto> listSub = new ArrayList<>();
        for(Category cat: subCategories){
            listSub.add(mapToDto(cat));
        }
        return listSub;
    }

    @Override
    public String createSubCategory(SubCategoryDto subCategoryDto) {
        Category superCategory = categoryRepository.findByName(subCategoryDto.getParentCategory());
        if(superCategory == null){
            System.out.println("Huy dep trai");
            throw new ResourceNotFoundException("Category", subCategoryDto.getParentCategory());
        }

        Category category = new Category();

        category.setParent(superCategory);
        category.setImage(subCategoryDto.getImage());
        category.setName(subCategoryDto.getName());
        category.setEnabled(subCategoryDto.isEnabled());
        category.setAlias(subCategoryDto.getAlias());

        categoryRepository.save(category);


        return "Create SubCategory Successfully";
    }

    @Override
    public CategoryDto updateCategory(Integer id, CategoryDto categoryDto) {

        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CAtegory", "id", id));

        if(categoryDto.getName() != null){
            category.setName(categoryDto.getName());
        }

        if(categoryDto.getAlias() != null){
            category.setAlias(categoryDto.getAlias());
        }

        if(categoryDto.isEnabled()){
            category.setEnabled(categoryDto.isEnabled());
        }


        categoryRepository.save(category);

        return mapToDto(category);
    }

    @Override
    public void deleteCategory(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        categoryRepository.delete(category);
    }

    @Override
    public CategoryDto listCategoryById(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));;
        return mapToDto(category);
    }

    @Override
    public List<CategoryDto> listCategoriesPagingAndSorting(int pageNo, int pageSize) {

        String sortBy = "alias";
        String sortDir = "asc";


        //Sort object
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        //Pageable object
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Category> page = categoryRepository.findAll(pageable);

        List<Category> categories = page.getContent();
        List<CategoryDto> cateDto = categories.stream().map(cate -> mapToDto(cate)).collect(Collectors.toList());

        return cateDto;
    }

    @Override
    public List<CategoryDto> searchCategories(String query) {
        List<CategoryDto> listCategoriesDto = categoryRepository.searchCategoryByName(query)
                                                .stream().map(category -> mapToDto(category)).collect(Collectors.toList());
        return listCategoriesDto;
    }


    private CategoryDto mapToDto(Category category){
        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setAlias(category.getAlias());
        categoryDto.setImage(category.getImage());
        categoryDto.setEnabled(category.isEnabled());



        return categoryDto;
    }




}
