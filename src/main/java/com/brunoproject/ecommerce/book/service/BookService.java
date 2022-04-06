package com.brunoproject.ecommerce.book.service;

import com.brunoproject.ecommerce.book.exceptions.BookNotFoundException;
import com.brunoproject.ecommerce.dao.ProductRepository;
import com.brunoproject.ecommerce.entities.Product;
import com.brunoproject.ecommerce.product.exceptions.ProductNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class BookService {

    private ProductRepository productRepository;

    public List<Product> getActiveBooks() {
        return productRepository.findAllActiveBooks();
    }

    public Product getActiveBookById(Long bookId) {
        return verifyIfProductIsActiveAndBook(bookId);
    }

    private Product verifyIfProductIsActiveAndBook(Long bookId) {
        return productRepository.findAllActiveBooks().stream()
                .filter(product -> product.getId().equals(bookId))
                .findAny()
                .orElseThrow(BookNotFoundException::new);
    }
}
