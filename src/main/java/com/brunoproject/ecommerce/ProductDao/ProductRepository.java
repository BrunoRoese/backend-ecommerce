package com.brunoproject.ecommerce.ProductDao;

import com.brunoproject.ecommerce.ProductEntities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<List<Product>> findAllByProductCategoryId(Long productCategoryId);

    Optional<Product> findProductByIdAndProductCategoryId(Long productId, Long productCategoryId);
}
