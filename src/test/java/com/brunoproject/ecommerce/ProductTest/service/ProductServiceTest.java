package com.brunoproject.ecommerce.ProductTest.service;

import com.brunoproject.ecommerce.ProductEntities.ProductDto;
import com.brunoproject.ecommerce.ProductUtils.Mapper.ProductMapper;
import com.brunoproject.ecommerce.ProductDao.ProductRepository;
import com.brunoproject.ecommerce.ProductEntities.Product;
import com.brunoproject.ecommerce.Product.exceptions.ProductNotFoundException;
import com.brunoproject.ecommerce.Product.service.ProductService;
import com.brunoproject.ecommerce.ProductUtils.Updater.ProductUpdater;
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
    @Mock
    private ProductMapper productMapper;

    @Test
    public void shouldReturnTheCompleteListOfActiveProducts() {
        var product = mock(Product.class);
        var listOfProducts = List.of(product);
        var listOfProductDto = List.of(mock(ProductDto.class));

        given(productRepository.findAll()).willReturn(listOfProducts);
        given(product.isActive()).willReturn(true);
        given(productMapper.convertListOfProducts(listOfProducts)).willReturn(listOfProductDto);

        List<ProductDto> result = productService.getAllActiveProducts();

        verify(productRepository).findAll();
        verify(productMapper).convertListOfProducts(listOfProducts);
        assertEquals(result, listOfProductDto);
    }

    @Test
    public void shouldReturnAProductGivenAValidId() {
        var product = mock(Product.class);
        var listOfAllProducts = List.of(product);
        var productDto = mock(ProductDto.class);

        given(productRepository.findAll()).willReturn(listOfAllProducts);
        given(product.getId()).willReturn(1L);
        given(productMapper.convertSingleProduct(product)).willReturn(productDto);

        var result = productService.getProduct(1L);

        verify(productRepository).findAll();
        assertEquals(result, productDto);
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