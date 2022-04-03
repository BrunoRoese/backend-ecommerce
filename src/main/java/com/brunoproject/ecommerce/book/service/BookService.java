package com.brunoproject.ecommerce.book.service;

import com.brunoproject.ecommerce.dao.ProductRepository;
import com.brunoproject.ecommerce.entities.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BookService {

    private ProductRepository productRepository;

    public List<Product> getActiveBooks() {
        return productRepository.findAllActiveBooks();
    }
}
