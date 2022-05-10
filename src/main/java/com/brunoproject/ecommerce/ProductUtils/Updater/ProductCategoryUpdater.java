package com.brunoproject.ecommerce.ProductUtils.Updater;

import com.brunoproject.ecommerce.ProductCategory.exceptions.ProductCategoryNotFound;
import com.brunoproject.ecommerce.ProductDao.ProductCategoryRepository;
import com.brunoproject.ecommerce.ProductEntities.ProductCategory;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductCategoryUpdater {
    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategory setNewProductCategory(ProductCategory oldProductCategory) {
        var newProductCategory = new ProductCategory();

        newProductCategory.setId(oldProductCategory.getId());
        newProductCategory.setCategoryName(oldProductCategory.getCategoryName());

        tryToDeleteOldProductCategory(oldProductCategory);

        return newProductCategory;
    }

    private void tryToDeleteOldProductCategory(ProductCategory oldProductCategory) {
        try {
            productCategoryRepository.delete(oldProductCategory);
        } catch (Exception e) {
            throw new ProductCategoryNotFound("Product category " + oldProductCategory.getId() + " wasn't found");
        }
    }
}
