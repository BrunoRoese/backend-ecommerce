package com.brunoproject.ecommerce.booktest;

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
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

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
}
