package com.codecool.shop.service;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.Order;
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
    private ShoppingCartDao shoppingCartDao;
    private OrderDao orderDao;

    public ProductService(ProductDao productDao,
                          ProductCategoryDao productCategoryDao,
                          SupplierDao productSupplierDataStore,
                          ShoppingCartDao shoppingCartDao
                          ) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
        this.productSupplierDao = productSupplierDataStore;
        this.shoppingCartDao = shoppingCartDao;
    }

    public ProductService(ProductDao productDao, ShoppingCartDao shoppingCartDao){
        this.shoppingCartDao = shoppingCartDao;
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

    public ProductService(ShoppingCartDao shoppingCartDao) {
        this.shoppingCartDao = shoppingCartDao;
    }

    public ProductService(ShoppingCartDao shoppingCartDao, OrderDao orderDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.orderDao = orderDao;
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
        return shoppingCartDao.getAll();
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCartDao.getCart();
    }

    public List<Supplier> getAllSuppliers() {
        return productSupplierDao.getAll();
    }

    public int getOrderId(Order order) {
        return orderDao.getOrderId(order);
    }

    public List<Product> getAllProducts(){
        return productDao.getAll();
    }

    public void addProductToShippingCart(int productId){
        Product product = productDao.find(productId);
        shoppingCartDao.add(product);
    }

    public void decreaseProductQty(int productId){
        Product product = productDao.find(productId);
        shoppingCartDao.remove(product);
    }

    public void removeProductFromCart(int productId) {
        Product product = productDao.find(productId);
        shoppingCartDao.deleteItem(product);
    }
}
