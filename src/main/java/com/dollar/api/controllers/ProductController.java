package com.dollar.api.controllers;

import com.dollar.api.models.Product;
import com.dollar.api.request.ProductRequest;
import com.dollar.api.response.ApiResponse;
import com.dollar.api.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api")
@ResponseBody
@Api(
        value = "Api",
        description = "operation to manage product"
)
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/saveProduct")
    @ApiOperation(
            value = "save product in base",
            httpMethod = "POST",
            notes = "create new product",
            authorizations = {@Authorization(value = "apiKey")}
    )
    public ResponseEntity<?> saveProduct(@RequestBody @Valid ProductRequest productRequest) {

        Product product = new Product().setName(productRequest.getName())
                .setPrice(productRequest.getPrice())
                .setQte(productRequest.getQte());
        productService.save(product, productRequest.getCategory());
        return new ResponseEntity<>(
                new ApiResponse("Product registered successfully !", true),
                HttpStatus.OK
        );
    }

    @GetMapping("/allProduct")
    @ApiOperation(
            value = "get all product",
            httpMethod = "GET",
            notes = "all product in database",
            authorizations = {@Authorization(value = "apiKey")}
    )
    public List<Product> allProduct() {
        return productService.all();
    }

    @GetMapping("/allProductByCategory")
    @ApiOperation(
            value = "get all product by category",
            httpMethod = "GET",
            notes = "all product by category in database",
            authorizations = {@Authorization(value = "apiKey")}
    )
    public List<Product> getAllProductByCategory() {
        return productService.getAllProductByCategory();
    }
    @GetMapping("/nbProductNbByVegetable")
    @ApiOperation(
            value = "get nb of product by vegetables",
            httpMethod = "GET",
            notes = "nb of product by vegetables  in database",
            authorizations = {@Authorization(value = "apiKey")}
    )
    public Long nbProductNbByVegetable(){
        return productService.getProductNbByVegetable();
    }

    @GetMapping("/nbProductNbByFruit")
    @ApiOperation(
            value = "get nb of product by fruit",
            httpMethod = "GET",
            notes = "nb of product by fruit",
            authorizations = {@Authorization(value = "apiKey")}
    )
    public Long getProductNbByFruit(){
        return productService.getProductNbByFruit();
    }

    @GetMapping("/nbProduct")
    @ApiOperation(
            value = "get nb of product",
            httpMethod = "GET",
            notes = "nb of product ",
            authorizations = {@Authorization(value = "apiKey")}
    )
    public Long getProductNb(){
        return productService.getProductNb();
    }

}
