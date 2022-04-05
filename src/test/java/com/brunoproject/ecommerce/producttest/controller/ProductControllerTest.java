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

        assertEquals(result, listOfConvertedProducts);
    }
}
