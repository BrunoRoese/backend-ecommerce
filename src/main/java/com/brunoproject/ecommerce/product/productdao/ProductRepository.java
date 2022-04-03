package com.brunoproject.ecommerce.product.productdao;

import com.brunoproject.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
