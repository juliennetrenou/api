package com.dollar.api.repositories;

import com.dollar.api.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(
            value = "SELECT count(*) FROM product WHERE product.category_id = 1",
            nativeQuery = true)
    Long getProductNbByFruit();

    @Query(
            value = "SELECT count(*) FROM product WHERE product.category_id = 2",
            nativeQuery = true)
    Long getProductNbByVegetable();

    @Query(
            value = "SELECT p.id, p.name, c.name, p.category_id ,price, qte FROM product p, category c WHERE p.category_id = c.id",
            nativeQuery = true)
    List<Product> getAllProductByCategory();
}
