package com.brunoproject.ecommerce.ProductByCategoryTest.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.brunoproject.ecommerce.ProductUtils.Mapper.ProductMapper;
import com.brunoproject.ecommerce.Product.exceptions.ProductNotFoundException;
import com.brunoproject.ecommerce.ProductCategory.exceptions.ProductCategoryIsEmpty;
import com.brunoproject.ecommerce.ProductCategory.service.ProductByCategoryService;
import com.brunoproject.ecommerce.ProductDao.ProductRepository;
import com.brunoproject.ecommerce.ProductEntities.Product;
import com.brunoproject.ecommerce.ProductEntities.ProductDto;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductByCategoryServiceTest {

    @InjectMocks
    private ProductByCategoryService productByCategoryService;

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;

    @Test
    public void shouldReturnAllProductsWithProvidedCategory() {
        var product = mock(Product.class);
        var productList = List.of(product);
        var convertedProductList = List.of(mock(ProductDto.class));

        given(productRepository.findAllByProductCategoryId(1L)).willReturn(Optional.of(productList));
        given(productMapper.convertListOfProducts(productList)).willReturn(convertedProductList);

        var result = productByCategoryService.getAllProductByCategory(1L);

        verify(productRepository).findAllByProductCategoryId(1L);
        verify(productMapper).convertListOfProducts(productList);
        assertEquals(result, convertedProductList);
    }

    @Test
    public void shouldThrowProductCategoryIsEmptyException() {
        ProductCategoryIsEmpty productCategoryIsEmpty = assertThrows(
                ProductCategoryIsEmpty.class,
                () -> {
                    given(productRepository.findAllByProductCategoryId(1L)).willReturn(Optional.empty());

                    productByCategoryService.getAllProductByCategory(1L);
                }
        );
    }

    @Test
    public void shouldReturnSpecificProductBasedOnHisId() {
        var product = mock(Product.class);
        var productDto = mock(ProductDto.class);

        given(productRepository.findProductByIdAndProductCategoryId(1L,1L)).willReturn(Optional.of(product));
        given(productMapper.convertSingleProduct(product)).willReturn(productDto);

        var result = productByCategoryService.getSpecificProductByCategory(1L,1L);

        assertEquals(result, productDto);
    }

    @Test
    public void shouldThrowProductNotFoundExceptionIfDoesntFindAnyProduct() {
        ProductNotFoundException productNotFoundException = assertThrows(
                ProductNotFoundException.class,
                () -> {
                    given(productRepository.findProductByIdAndProductCategoryId(1L,1L)).willReturn(Optional.empty());

                    productByCategoryService.getSpecificProductByCategory(1L,1L);
                }
        );
    }
}
