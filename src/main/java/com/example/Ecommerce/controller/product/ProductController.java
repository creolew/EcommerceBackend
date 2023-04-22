package com.example.Ecommerce.controller.product;


import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.exception.ResourceNotFoundException;
import com.example.Ecommerce.payload.product.ProductDto;
import com.example.Ecommerce.payload.user.UserDto;
import com.example.Ecommerce.response.ResponseMessage;
import com.example.Ecommerce.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product/v1")
public class ProductController {

    @Autowired
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAllProducts")
    public List<ProductDto> listAll(){
        return productService.listAll();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN')")
    @PostMapping("/createProduct/{categoryId}")
    public String createProduct(@RequestBody ProductDto productDto, @PathVariable(name = "categoryId") int categoryId){
        productService.createProduct(productDto, categoryId);
        return "Create product successfully";
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN')")
    @PutMapping("/updateProduct/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable(name = "productId") int productId,@RequestBody ProductDto productDto){
        ProductDto product=productService.updateProduct(productId, productDto);
        return new ResponseEntity<ProductDto>(product, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN')")
    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity<ResponseMessage> deleteProduct(@PathVariable(name = "productId") int productId) {
        productService.deleteProduct(productId);
        ResponseMessage responseMessage = new ResponseMessage("Delete product successfully", HttpStatus.OK.value());
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);

    }

    @PreAuthorize("hasAnyRole('ROLE_SHIPPER', 'ROLE_SELLER')")
    @GetMapping("/getProductById/{productId}")
    public ProductDto getProductById(@PathVariable(name = "productId") int productId){
        productService.getProductById(productId);
        return productService.getProductById(productId);
    }

    @PreAuthorize("hasAnyRole('ROLE_SHIPPER', 'ROLE_SELLER')")
    @GetMapping("/getProductByCategoryId/{categoryId}")
    public List<ProductDto> getProductByCategoryId(@PathVariable(name = "categoryId") int categoryId){
        List<ProductDto> productDtoList= productService.getProductByCategoryId(categoryId);
        return productDtoList;
    }




}
