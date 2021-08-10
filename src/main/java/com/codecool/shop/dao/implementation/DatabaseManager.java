package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.*;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DatabaseManager {
    private static DatabaseManager instance = null;

    private OrderDao orderDao;
    private ProductCategoryDao productCategoryDao;
    private ProductDao productDao;
    private ShoppingCartDao shoppingCartDao;
    private SupplierDao supplierDao;

    private DatabaseManager() {
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        orderDao = OrderDaoMem.getInstance(dataSource);
        productCategoryDao = ProductCategoryDaoMem.getInstance(dataSource);
        productDao = ProductDaoMem.getInstance(dataSource);
        shoppingCartDao = ShoppingCartDaoMem.getInstance(dataSource);
        supplierDao = SupplierDaoMem.getInstance(dataSource);
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public ProductCategoryDao getProductCategoryDao() {
        return productCategoryDao;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public ShoppingCartDao getShoppingCartDao() {
        return shoppingCartDao;
    }

    public SupplierDao getSupplierDao() {
        return supplierDao;
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setDatabaseName(System.getenv("PSQL_NAME"));
        dataSource.setUser(System.getenv("PSQL_USER"));
        dataSource.setPassword(System.getenv("PSQL_PASSWORD"));

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
