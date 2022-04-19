package com.brunoproject.ecommerce.book.service;

import com.brunoproject.ecommerce.book.exceptions.BookNotFoundException;
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

    public Product getActiveBookById(Long bookId) {
        return verifyIfProductIsActiveAndBook(bookId);
    }

    private Product verifyIfProductIsActiveAndBook(Long bookId) {
        var product = productRepository.getById(bookId);

        if (product.isActive()) {
            return product;
        }

        throw new RuntimeException("Product with id " + product.getId() + " is not currently active");
    }
}
