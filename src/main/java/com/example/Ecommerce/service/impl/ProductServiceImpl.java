package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.entity.Category;
import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.entity.User;
import com.example.Ecommerce.exception.EcommerceAPIException;
import com.example.Ecommerce.exception.ResourceNotFoundException;
import com.example.Ecommerce.payload.product.ProductDto;
import com.example.Ecommerce.repository.CategoryRepository;
import com.example.Ecommerce.repository.ProductRepository;
import com.example.Ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<ProductDto> listAll() {

        List<ProductDto> listProductDto = productRepository.findAll().stream().map(product -> mapToDto(product)).collect(Collectors.toList());
        return listProductDto;
    }

    @Override
    public void createProduct(ProductDto productDto, int categoryId) {
        Category category = categoryRepository.findById(categoryId).get();

        Product product = new Product();

        product.setId(productDto.getId());
        product.setCost(productDto.getCost());
        product.setCategory(category);
        product.setName(productDto.getName());
        product.setCreatedTime(new Date());
        product.setUpdatedTime(new Date());
        product.setDescription(productDto.getDescription());
        product.setEnabled(productDto.isEnabled());

        productRepository.save(product);

    }

    @Override
    public ProductDto updateProduct(int productId, ProductDto productDto) {
        Product product = productRepository.findById(productId).get();

        if(productDto.getCost() != null){
            product.setCost(productDto.getCost());
        }
        if(productDto.getDescription() != null){
            product.setDescription(productDto.getDescription());
        }

        if(productDto.getCategory() != null){
            Category category = categoryRepository.findByName(productDto.getCategory());
            product.setCategory(category);
        }

        if(productDto.getName() != null){
            product.setName(productDto.getName());
        }

        if(productDto.isEnabled()){
            product.setEnabled(true);
        }

        product.setUpdatedTime(new Date());

        productRepository.save(product);

        return mapToDto(product);
    }

    @Override
    public void deleteProduct(int productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        productRepository.delete(product);
    }

    @Override
    public ProductDto getProductById(int productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));



        return mapToDto(product);
    }

    @Override
    public  List<ProductDto> getProductByCategoryId(int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", categoryId));;
        List<Product> listProduct = productRepository.findByCategoryId(categoryId);
        List<ProductDto> listProductDto = new ArrayList<>();

        for(Product p : listProduct){
            listProductDto.add(mapToDto(p));
        }

        return listProductDto;
    }


    private ProductDto mapToDto(Product product){
        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setEnabled(product.isEnabled());
        productDto.setCost(product.getCost());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(product.getCategory().getName());
        productDto.setCreatedTime(product.getCreatedTime());
        productDto.setUpdatedTime(product.getUpdatedTime());

        return productDto;
    }
}
