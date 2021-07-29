package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.product.Product;
import com.codecool.shop.model.product.ProductCategory;
import com.codecool.shop.model.product.Supplier;
import com.codecool.shop.model.shoppingCart.ShoppingCart;

import java.util.HashMap;
import java.util.List;

public class ProductService{
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private SupplierDao productSupplierDao;
    private ShoppingCartDao shoppingCart;

    public ProductService(ProductDao productDao,
                          ProductCategoryDao productCategoryDao,
                          SupplierDao productSupplierDataStore,
                          ShoppingCartDao shoppingCart
                          ) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
        this.productSupplierDao = productSupplierDataStore;
        this.shoppingCart = shoppingCart;


    }

    public ProductService(ProductDao productDao, ShoppingCartDao shoppingCartDao){
        this.shoppingCart = shoppingCartDao;
        this.productDao = productDao;
    }

    public ProductService(ProductDao productDao,
                          ProductCategoryDao productCategoryDao,
                          SupplierDao productSupplierDataStore
    ) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
        this.productSupplierDao = productSupplierDataStore;

    }

    public ProductService(ShoppingCartDao shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public ProductCategory getProductCategory(int categoryId){
        return productCategoryDao.find(categoryId);
    }

    public List<Product> getProductsForCategory(int categoryId){
        var category = productCategoryDao.find(categoryId);
        return productDao.getBy(category);
    }

    public List<Product> getProductsForSupplier(int supplierId){
        var supplier = productSupplierDao.find(supplierId);
        return productDao.getBy(supplier);
    }

    public List<Product> getProductsForCategoryAndSupplier(int categoryId, int supplierId){
        var category = productCategoryDao.find(categoryId);
        var supplier = productSupplierDao.find(supplierId);
        return productDao.getBy(category, supplier);
    }

    public List<ProductCategory> getAllCategory(){
        return productCategoryDao.getAll();
    }

    public HashMap<Product,Integer> getAllCartItems(){
        return shoppingCart.getAll();
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart.getCart();
    }

    public List<Supplier> getAllSuppliers() {
        return productSupplierDao.getAll();
    }
}
