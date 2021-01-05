package com.dollar.api.controllers;

import com.dollar.api.models.Category;
import com.dollar.api.models.Employee;
import com.dollar.api.response.ApiResponse;
import com.dollar.api.services.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
@ResponseBody
@Api(
        value = "Api",
        description = "operation to manage category"
)
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping("/saveCategory")
    @ApiOperation(
            value = "save category in base",
            httpMethod = "POST",
            notes = "create new category",
            authorizations = {@Authorization(value = "apiKey")}
    )
    public ResponseEntity<?> saveCategory(@RequestBody Category category) {
        categoryService.save(category);
        return new ResponseEntity<>(
                new ApiResponse("Category registered successfully !", true),
                HttpStatus.OK
        );
    }

    @GetMapping("/allCategory")
    @ApiOperation(
            value = "get all category",
            httpMethod = "GET",
            notes = "all category in database",
            authorizations = {@Authorization(value = "apiKey")}
    )
    public List<Category> allCategory(){
        return categoryService.all();
    }


}
