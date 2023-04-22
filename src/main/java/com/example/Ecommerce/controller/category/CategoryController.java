package com.example.Ecommerce.controller.category;

import com.example.Ecommerce.payload.category.CategoryDto;
import com.example.Ecommerce.payload.category.SubCategoryDto;
import com.example.Ecommerce.response.ResponseMessage;
import com.example.Ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category/v1")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN')")
    @GetMapping("/getAllCategories")
    public List<CategoryDto> getAllCategories(){
        return categoryService.listAllCategories();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN')")
    @GetMapping("/getCategoryById/{id}")
    public CategoryDto getCategoryById(@PathVariable(name = "id") Integer id){
        return categoryService.listCategoryById(id);
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN')")
    @GetMapping("/getSubCategories")
    public List<CategoryDto> getSubList(@RequestParam(name = "superCategory") String superCategory){
        return categoryService.listSubCategories(superCategory);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN')")
    @PostMapping("/createSubCategory")
    public String createSubCategory(@RequestBody SubCategoryDto subCategoryDto){
        return categoryService.createSubCategory(subCategoryDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN')")
    @PutMapping("updateCategory/{id}")
    public ResponseEntity<ResponseMessage> updateUser(@PathVariable(name = "id") Integer id, @RequestBody CategoryDto categoryDto){
        CategoryDto tempCategoryDto = categoryService.updateCategory(id, categoryDto);

        ResponseMessage responseMessage = new ResponseMessage("Update user successfully", HttpStatus.OK.value());

        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN')")
    @DeleteMapping("deleteUser/{id}")
    public ResponseEntity<ResponseMessage> deleteUser(@PathVariable(name = "id") Integer id){
        categoryService.deleteCategory(id);
        ResponseMessage responseMessage = new ResponseMessage("Delete user successfully", HttpStatus.OK.value());

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN')")
    @GetMapping("/getCategoriesPagingAndSorting")
    public List<CategoryDto> listCategoryPagingAndSorting(@RequestParam(name = "pageNo") int pageNo, @RequestParam(name = "pageSize") int pageSize){
        return categoryService.listCategoriesPagingAndSorting(pageNo,pageSize);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN')")
    @GetMapping("/searchCategoriesByNameOrAlias")
    public List<CategoryDto> searchCategoriesByNameOrAlias(@RequestParam(name = "query") String query){
        return categoryService.searchCategories(query);
    }



}
