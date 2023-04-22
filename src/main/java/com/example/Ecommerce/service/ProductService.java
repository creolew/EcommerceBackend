package com.example.Ecommerce.service;

import com.example.Ecommerce.payload.product.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> listAll();
    void createProduct(ProductDto productDto, int categoryId);

    ProductDto updateProduct(int productId, ProductDto productDto);

    void deleteProduct(int productId);

    ProductDto getProductById(int productId);

    List<ProductDto> getProductByCategoryId(int categoryId);
}
