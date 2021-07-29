package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.product.Product;
import com.codecool.shop.model.product.ProductCategory;
import com.codecool.shop.model.product.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        ShoppingCartDao shoppingCart = ShoppingCartDaoMem.getInstance();

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
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);

        ProductCategory laptop = new ProductCategory("Laptop", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(laptop);

        ProductCategory bus = new ProductCategory("Bus", "Vehicle", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(bus);

        ProductCategory cat3 = new ProductCategory("cat3", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(cat3);

        ProductCategory cat4 = new ProductCategory("cat4", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(cat4);

        ProductCategory cat5 = new ProductCategory("cat5", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(cat5);

        //setting up products and printing it

        productDataStore.add(new Product("Amazon Fire", 49.1f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, anonymous));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 491, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, d3f4ult));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", laptop, theCalculator));

        shoppingCart.add(productDataStore.find(1));
        shoppingCart.add(productDataStore.find(3));
        shoppingCart.add(productDataStore.find(3));
        shoppingCart.add(productDataStore.find(3));
        shoppingCart.add(productDataStore.find(3));
        shoppingCart.add(productDataStore.find(2));
        shoppingCart.add(productDataStore.find(2));
        shoppingCart.add(productDataStore.find(2));
        shoppingCart.add(productDataStore.find(2));
        shoppingCart.add(productDataStore.find(2));
        shoppingCart.add(productDataStore.find(1));
        shoppingCart.add(productDataStore.find(1));
        shoppingCart.add(productDataStore.find(1));

    }
}
