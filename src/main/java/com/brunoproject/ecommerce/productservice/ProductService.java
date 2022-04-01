package com.brunoproject.ecommerce.productservice;

import com.brunoproject.ecommerce.dao.ProductRepository;
import com.brunoproject.ecommerce.entities.Product;
import com.brunoproject.ecommerce.productexceptions.ProductListIsEmptyException;
import com.brunoproject.ecommerce.productexceptions.ProductNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductUpdater productUpdater;

    public List<Product> getAllActiveProducts() {
         var productList = productRepository.findAll()
                 .stream()
                 .filter(Product::isActive)
                 .collect(Collectors.toList());

         if (productList.isEmpty()) {
             throw new ProductListIsEmptyException();
         }

         return productList;
    }

    public Product getProduct(Long id) {
        return verifyIfProductExists(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public void updateProduct(Long id, Product informationToUpdate) {
        Product productBeingUpdated = verifyIfProductExists(id);

        productRepository.save(productUpdater.saveProductWithNewValues(productBeingUpdated, informationToUpdate));
    }

    public void toggleActivationProduct(Long id) {
        Product productBeingUpdated = verifyIfProductExists(id);

        productBeingUpdated.setActive(!productBeingUpdated.isActive());

        productRepository.save(productBeingUpdated);
    }

    public void deleteProduct(Long id) {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException();
        }
    }

    private Product verifyIfProductExists(Long id) {
        return productRepository.findAll().stream()
                .filter(productBeingCompared -> id.equals(productBeingCompared.getId()))
                .findAny()
                .orElseThrow(ProductNotFoundException::new);
    }
}