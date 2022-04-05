package com.brunoproject.ecommerce.producttest.service;

import com.brunoproject.ecommerce.dao.ProductRepository;
import com.brunoproject.ecommerce.entities.Product;
import com.brunoproject.ecommerce.product.exceptions.ProductNotFoundException;
import com.brunoproject.ecommerce.product.service.ProductService;
import com.brunoproject.ecommerce.product.service.ProductUpdater;
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
    @Mock
    private ProductUpdater productUpdater;

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

    @Test
    public void shouldUpdateProduct() {
        var product = mock(Product.class);
        var listOfProducts = List.of(product);
        var informationToUpdateProduct = mock(Product.class);

        given(product.getId()).willReturn(1L);
        given(productRepository.findAll()).willReturn(listOfProducts);
        given(productUpdater.saveProductWithNewValues(product, informationToUpdateProduct)).willReturn(informationToUpdateProduct);
        given(productRepository.save(informationToUpdateProduct)).willReturn(informationToUpdateProduct);

        var result = productService.updateProduct(1L, informationToUpdateProduct);

        verify(productUpdater).saveProductWithNewValues(product, informationToUpdateProduct);
        verify(productRepository).findAll();
        assertEquals(informationToUpdateProduct, result);
    }

    @Test
    public void shouldActivateProduct() {
        var productBeingActivated = mock(Product.class);
        var productList = List.of(productBeingActivated);

        given(productBeingActivated.getId()).willReturn(1L);
        given(productRepository.findAll()).willReturn(productList);
        given(productBeingActivated.isActive()).willReturn(false);
        given(productRepository.save(productBeingActivated)).willReturn(productBeingActivated);

        productService.toggleActivationProduct(1L);

        assertFalse(productBeingActivated.isActive());
    }
}