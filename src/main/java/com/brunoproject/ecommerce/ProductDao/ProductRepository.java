package com.brunoproject.ecommerce.ProductDao;

import com.brunoproject.ecommerce.ProductEntities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select * from product where category_id = 1 and active = 1",
            nativeQuery = true)
    List<Product> findAllActiveBooks();

    List<Product> findAllByProductCategoryId(Long productCategoryId);
}
