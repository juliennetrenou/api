package com.dollar.api.services;

import com.dollar.api.models.Category;
import com.dollar.api.models.Product;
import com.dollar.api.repositories.CategoryRepository;
import com.dollar.api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    public Product save(Product product, Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            product.setCategory(category.get());
        }
        return productRepository.save(product);
    }

    public List<Product> all(){
        return productRepository.findAll();
    }

    public List<Product> getAllProductByCategory(){
        return productRepository.getAllProductByCategory();
    }

    public Long getProductNbByFruit(){
        return productRepository.getProductNbByFruit();
    }

    public Long getProductNbByVegetable(){
        return productRepository.getProductNbByVegetable();
    }

    public Long getProductNb(){
        return productRepository.count();
    }
}
