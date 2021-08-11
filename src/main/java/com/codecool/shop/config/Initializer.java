package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.dao.implementation.MEM.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.MEM.ProductDaoMem;
import com.codecool.shop.dao.implementation.MEM.ShoppingCartDaoMem;
import com.codecool.shop.dao.implementation.MEM.SupplierDaoMem;
import com.codecool.shop.model.product.Product;
import com.codecool.shop.model.product.ProductCategory;
import com.codecool.shop.model.product.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

@WebListener
public class Initializer implements ServletContextListener {

    private DatabaseManager dbManager;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        setupDbManager(); // in case of database configuration


        // in case of app memory configuration
//        ProductDao productDataStore = ProductDaoMem.getInstance();
//        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
//        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
//        ShoppingCartDao shoppingCart = ShoppingCartDaoMem.getInstance();
//        LoadJDBCSettingsFromPropertiesFile properties = new LoadJDBCSettingsFromPropertiesFile();
//        properties.load();
//
//
//        //setting up a new supplier
//        Supplier bkk = new Supplier("BKK");
//        supplierDataStore.add(bkk);
//        Supplier anonymous = new Supplier("Anonymous");
//        supplierDataStore.add(anonymous);
//        Supplier neo = new Supplier("Neo");
//        supplierDataStore.add(neo);
//        Supplier trinity = new Supplier("Trinity");
//        supplierDataStore.add(trinity);
//        Supplier d3f4ult = new Supplier("D3F4ULT");
//        supplierDataStore.add(d3f4ult);
//        Supplier theCalculator = new Supplier("The Calculator");
//        supplierDataStore.add(theCalculator);
//        Supplier mrRobot = new Supplier("Mr.Robot");
//        supplierDataStore.add(mrRobot);
//        Supplier m1ck3y = new Supplier("M1CK3Y");
//        supplierDataStore.add(m1ck3y);
//
//        //setting up a new product category
//        ProductCategory hardware = new ProductCategory("Hardware");
//        productCategoryDataStore.add(hardware);
//
//        ProductCategory software = new ProductCategory("Software");
//        productCategoryDataStore.add(software);
//
//        ProductCategory merchandise = new ProductCategory("Merchandise");
//        productCategoryDataStore.add(merchandise);
//
//        ProductCategory stolenData = new ProductCategory("Stolen data");
//        productCategoryDataStore.add(stolenData);
//
//        ProductCategory service = new ProductCategory("Services");
//        productCategoryDataStore.add(service);
//
//
//        //setting up products and printing it
//
//
//        productDataStore.add(new Product("Hacker Laptop", 949.1f, "USD", "hacker_laptop.jpg", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", hardware, anonymous));
//        productDataStore.add(new Product("My first hacking laptop", 491, "USD", "kid_laptop.png", "Keep your baby's little hands (and mind) busy as can be with this interactive toy laptop that introduces password cracking, stealing data and more!", hardware, d3f4ult));
//        productDataStore.add(new Product("Cracked curriculum", 89, "USD", "curriculum.png", "Finish Codecool in one day! All tasks with solutions", software, theCalculator));
//        productDataStore.add(new Product("Cracking a password", 8.5f, "USD","crack_password.jpg", "Cracking any password", service, theCalculator));
//        productDataStore.add(new Product("Attend attendance", 2f, "USD", "attendance.jpg", "Attending attendance instead of you: being on time and great answer for attendance question included", service, trinity));
//        productDataStore.add(new Product("Passing a PA", 20f, "USD", "PA.jpg", "We will take your PA for you, all green scores guaranteed!", service, mrRobot));
//        productDataStore.add(new Product("Anonymous mask", 15.5f, "USD", "mask.jpg", "No one will know that you are a hacker if you wear this", merchandise, anonymous));
//        productDataStore.add(new Product("Credit card credentials", 15000000f, "USD", "card.jpg", "Best credit card data with a lot of money", stolenData, m1ck3y));
//        productDataStore.add(new Product("Hoodie", 150, "USD", "hoodie.jpg", "A must have for every real hacker", merchandise, neo));
//        productDataStore.add(new Product("105 bus", 1, "USD","105bus.jpg", "Bus to get you everywhere you want with your hacker friends. Bus connecting people", hardware, bkk));

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
