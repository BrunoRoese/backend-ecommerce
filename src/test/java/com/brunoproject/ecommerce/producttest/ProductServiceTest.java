package com.brunoproject.ecommerce.producttest;

import com.brunoproject.ecommerce.dao.ProductRepository;
import com.brunoproject.ecommerce.entities.Product;
import com.brunoproject.ecommerce.productexceptions.ProductNotFoundException;
import com.brunoproject.ecommerce.productservice.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void shouldReturnTheCompleteListOfActiveProducts() {
        var product = mock(Product.class);
        var listOfProducts = List.of(product);

        given(productRepository.findAll()).willReturn(listOfProducts);
        given(product.isActive()).willReturn(true);

        List<Product> result = productService.getAllActiveProducts();

        verify(productRepository).findAll();
        assertEquals(result, listOfProducts);
    }

    @Test
    public void shouldReturnAProductGivenAValidId() {
        var product = mock(Product.class);
        var listOfAllProducts = List.of(product);

        when(productRepository.findAll()).thenReturn(listOfAllProducts);
        when(product.getId()).thenReturn(1L);

        var result = productService.getProduct(1L);

        verify(productRepository).findAll();
        assertEquals(result, product);
    }

    @Test
    public void shouldThrowAExceptionIfThereIsNoProductWithGivenId() {
        ProductNotFoundException productNotFoundException = assertThrows(
                ProductNotFoundException.class,
                () -> {
                    var product = mock(Product.class);
                    var listOfAllProducts = List.of(product);

                    when(productRepository.findAll()).thenReturn(listOfAllProducts);
                    when(product.getId()).thenReturn(1L);

                    productService.getProduct(2L);
                }
        );
    }

    @Test
    public void shouldSaveProduct() {
        var product = mock(Product.class);

        given(productRepository.save(product)).willReturn(product);

        var result = productService.createProduct(product);

        verify(productRepository).save(product);
        assertEquals(product, result);
    }
}