package com.brunoproject.ecommerce.ProductTest.controller;

import com.brunoproject.ecommerce.ProductEntities.ProductDto;
import com.brunoproject.ecommerce.ProductEntities.Product;
import com.brunoproject.ecommerce.Product.controller.ProductController;
import com.brunoproject.ecommerce.Product.service.ProductService;
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
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Test
    public void shouldReturnAListOfCovertedProducts() {
        var listOfConvertedProducts = List.of(mock(ProductDto.class), mock(ProductDto.class));

        given(productService.getAllActiveProducts()).willReturn(listOfConvertedProducts);

        var result = productController.getAllActiveProducts();

        verify(productService).getAllActiveProducts();
        assertEquals(result, listOfConvertedProducts);
    }

    @Test
    public void shouldGetSelectedProduct() {
        var productDto = mock(ProductDto.class);

        given(productService.getProduct(1L)).willReturn(productDto);

        var result = productController.getProduct(1L);

        verify(productService).getProduct(1L);
        assertEquals(result, productDto);
    }

    @Test
    public void shouldCreateProduct() {
        var product = mock(Product.class);

        given(productService.createProduct(product)).willReturn(product);

        var result = productController.createProduct(product);

        verify(productService).createProduct(product);
        assertEquals(result, product);
    }

    @Test
    public void shouldUpdateProduct() {
        var product = new Product();
        var productToBeUpdated = new Product();

        product.setId(1L);
        product.setName("Product");
        productToBeUpdated.setName("ProductToBeUpdated");

        given(productService.updateProduct(1L, productToBeUpdated)).willReturn(productToBeUpdated);

        var result = productController.updateProduct(1L, productToBeUpdated);

        verify(productService).updateProduct(1L, productToBeUpdated);
        assertEquals(result.getName(), productToBeUpdated.getName());
    }

    @Test
    public void shouldReturnActivatedProduct() {
        var product = mock(Product.class);

        given(productService.toggleActivationProduct(1L)).willReturn(product);
        given(product.isActive()).willReturn(true);

        var result = productController.toggleActivationProduct(1L);

        assertTrue(result.isActive());
    }
}
