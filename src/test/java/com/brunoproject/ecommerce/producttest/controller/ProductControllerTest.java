package com.brunoproject.ecommerce.producttest.controller;

import com.brunoproject.ecommerce.converter.ProductConverter;
import com.brunoproject.ecommerce.converter.ProductDto;
import com.brunoproject.ecommerce.entities.Product;
import com.brunoproject.ecommerce.product.controller.ProductController;
import com.brunoproject.ecommerce.product.service.ProductService;
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
    @Mock
    private ProductConverter productConverter;

    @Test
    public void shouldReturnAListOfCovertedProducts() {
        var listOfProducts = List.of(mock(Product.class), mock(Product.class));
        var listOfConvertedProducts = List.of(mock(ProductDto.class), mock(ProductDto.class));

        given(productService.getAllActiveProducts()).willReturn(listOfProducts);
        given(productConverter.convertListOfProducts(listOfProducts)).willReturn(listOfConvertedProducts);

        var result = productController.getAllProducts();

        verify(productService).getAllActiveProducts();
        verify(productConverter).convertListOfProducts(listOfProducts);
        assertEquals(result, listOfConvertedProducts);
    }

    @Test
    public void shouldGetSelectedProduct() {
        var product = mock(Product.class);
        var convertedProduct = mock(ProductDto.class);

        given(productService.getProduct(1L)).willReturn(product);
        given(productConverter.convertSingleProduct(product)).willReturn(convertedProduct);

        var result = productController.getProduct(1L);

        verify(productService).getProduct(1L);
        verify(productConverter).convertSingleProduct(product);
        assertEquals(result, convertedProduct);
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
