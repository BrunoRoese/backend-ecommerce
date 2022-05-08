package com.brunoproject.ecommerce.Product.service;

import com.brunoproject.ecommerce.ProductEntities.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductUpdater {

    public Product saveProductWithNewValues(Product productBeingUpdated, Product informationToUpdate) {
        productBeingUpdated.setProductCategory(informationToUpdate.getProductCategory());
        productBeingUpdated.setSku(informationToUpdate.getSku());
        productBeingUpdated.setName(informationToUpdate.getName());
        productBeingUpdated.setDescription(informationToUpdate.getDescription());
        productBeingUpdated.setUnitPrice(informationToUpdate.getUnitPrice());
        productBeingUpdated.setImageUrl(informationToUpdate.getImageUrl());
        productBeingUpdated.setActive(informationToUpdate.isActive());
        productBeingUpdated.setUnitsInStock(informationToUpdate.getUnitsInStock());

        return productBeingUpdated;
    }
}
