package com.brunoproject.ecommerce.booktest;

import com.brunoproject.ecommerce.book.exceptions.BookNotFoundException;
import com.brunoproject.ecommerce.book.service.BookService;
import com.brunoproject.ecommerce.dao.ProductRepository;
import com.brunoproject.ecommerce.entities.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;
    @Mock
    private ProductRepository productRepository;

    @Test
    public void shouldReturnAllActiveBooks() {
        var bookOne = mock(Product.class);
        var bookTwo = mock(Product.class);
        var bookList = List.of(bookOne, bookTwo);

        given(productRepository.findAllActiveBooks()).willReturn(bookList);

        var result = bookService.getActiveBooks();

        verify(productRepository).findAllActiveBooks();
        assertEquals(result, bookList);
    }

    @Test
    public void shouldGetActiveBook() {
        var book = mock(Product.class);
        var listOfBooks = List.of(book);

        given(productRepository.findAllActiveBooks()).willReturn(listOfBooks);
        given(book.getId()).willReturn(1L);

        var result = bookService.getActiveBookById(1L);

        verify(productRepository).findAllActiveBooks();
        assertEquals(result, book);
    }

    @Test
    public void shouldThrowExceptionIfProductIsNotFound() {
        BookNotFoundException productNotFoundException = assertThrows(
                BookNotFoundException.class,
                () -> {
                    var book = mock(Product.class);
                    var listOfBooks = List.of(book);

                    given(productRepository.findAllActiveBooks()).willReturn(listOfBooks);
                    given(book.getId()).willReturn(2L);

                    bookService.getActiveBookById(1L);
                }
        );
    }
}
