package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

import com.codecool.shop.model.product.ProductCategory;
import com.codecool.shop.model.product.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class Initializer implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(Initializer.class);

    private DatabaseManager dbManager;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        ShoppingCartDao shoppingCart = ShoppingCartDaoMem.getInstance();
//        logger.debug("Temperature set e");
//        logger.warn("Temperature has risen above 50 degrees.");
//        logger.info("Temperature has risen above 30 degrees.");

        //setting up a new supplier
        Supplier bkk = new Supplier("BKK", "Buses");
        supplierDataStore.add(bkk);
        Supplier anonymous = new Supplier("Anonymous", "Hacker stuffs");
        supplierDataStore.add(anonymous);
        Supplier neo = new Supplier("Neo", "Matrix");
        supplierDataStore.add(neo);
        Supplier trinity = new Supplier("Trinity", "Matrix");
        supplierDataStore.add(trinity);
        Supplier d3f4ult = new Supplier("D3F4ULT", "Computers");
        supplierDataStore.add(d3f4ult);
        Supplier theCalculator = new Supplier("The Calculator", "Computers");
        supplierDataStore.add(theCalculator);
        Supplier mrRobot = new Supplier("Mr.Robot", "Hacker stuffs");
        supplierDataStore.add(mrRobot);
        Supplier m1ck3y = new Supplier("M1CK3Y", "Computers");
        supplierDataStore.add(m1ck3y);

        //setting up a new product category
        ProductCategory hardware = new ProductCategory("Hardware", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(hardware);

        ProductCategory software = new ProductCategory("Software", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(software);

        ProductCategory merchandise = new ProductCategory("Merchandise", "Vehicle", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(merchandise);

        ProductCategory stolenData = new ProductCategory("Stolen data", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(stolenData);

        ProductCategory service = new ProductCategory("Services", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(service);
        setupDbManager();
    }

    private void setupDbManager () {
        dbManager = DatabaseManager.getInstance();
        try {
            dbManager.setup();
        } catch (SQLException ex) {
            System.out.println("Cannot connect to database.");
            System.out.println(ex.getMessage());
        }
    }
}
