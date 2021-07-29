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


        //setting up products and printing it

        productDataStore.add(new Product("Hacking Laptop","hacker_laptop.jpg", 949.1f, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", hardware, anonymous));
        productDataStore.add(new Product("My first hacking laptop", "kid_laptop.png",491, "USD", "Keep your baby’s little hands (and mind) busy as can be with this interactive toy laptop that introduces password cracking, stealing data and more!\n" +
            "This toy laptop keeps babies busy with lots of hands-on play! As your little computer whiz presses, flips, and slides the different activities, they'll hear exciting songs, sounds, and phrases that introduce numbers, colors, shapes, and more!", hardware, d3f4ult));
        productDataStore.add(new Product("Cracked curriculum", "curriculum.png", 89, "USD", "Finish Codecool in one day! All tasks with solutions", software, theCalculator));
        productDataStore.add(new Product("Cracking a password", "crack_password.jpg", 8.5f, "USD", "Cracking any password", service, theCalculator));
        productDataStore.add(new Product("Attend attendance", "attendance.jpg", 2f, "USD", "Attending attendance instead of you: being on time and great answer for attendance question included", service, trinity));
        productDataStore.add(new Product("Passing a PA", "PA.jpg", 20f, "USD", "We will take your PA for you, all green scores guaranteed!", service, mrRobot));


    }
}
