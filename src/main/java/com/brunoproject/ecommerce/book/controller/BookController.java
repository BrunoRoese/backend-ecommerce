package com.brunoproject.ecommerce.book.controller;

import com.brunoproject.ecommerce.book.service.BookService;
import com.brunoproject.ecommerce.mapper.ProductMapper;
import com.brunoproject.ecommerce.entities.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/book")
public class BookController {

    private BookService bookService;
    private ProductMapper productMapper;

    @GetMapping()
    public List<ProductDto> getBooks() {
        var books = bookService.getActiveBooks();

        return productMapper.convertListOfProducts(books);
    }

    @GetMapping("/{bookId}")
    public ProductDto getBookById(@PathVariable Long bookId) {
        var book = bookService.getActiveBookById(bookId);

        return productMapper.convertSingleProduct(book);
    }
}
