package com.brunoproject.ecommerce.book.controller;

import com.brunoproject.ecommerce.book.service.BookService;
import com.brunoproject.ecommerce.converter.ProductConverter;
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
    private ProductConverter productConverter;

    @GetMapping()
    public List<ProductDto> getBooks() {
        var books = bookService.getActiveBooks();

        return productConverter.convertListOfProducts(books);
    }

    @GetMapping("/{bookId}")
    public ProductDto getBookById(@PathVariable Long bookId) {
        var book = bookService.getActiveBookById(bookId);

        return productConverter.convertSingleProduct(book);
    }
}
