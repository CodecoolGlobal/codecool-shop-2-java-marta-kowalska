package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.JDBC.*;
import com.codecool.shop.dao.implementation.MEM.*;
import com.codecool.shop.model.product.Product;
import com.codecool.shop.model.product.ProductCategory;
import com.codecool.shop.model.product.Supplier;
import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DatabaseManager {
    private static DatabaseManager instance = null;
    private Logger logger = LoggerFactory.getLogger(DatabaseManager.class);
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

    public void setup(String dbName, String userName, String password) throws SQLException {
        DataSource dataSource = connect(dbName, userName, password);
        orderDao = OrderDaoJdbc.getInstance(dataSource);
        productCategoryDao = ProductCategoryDaoJdbc.getInstance(dataSource);
        productDao = ProductDaoJdbc.getInstance(dataSource);
        shoppingCartDao = ShoppingCartDaoJdbc.getInstance(dataSource);
        supplierDao = SupplierDaoJdbc.getInstance(dataSource);
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

    private DataSource connect(String dbName, String userName, String password) throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(userName);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        logger.info("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");
        logger.info("Connection ok.");
        logger.info("Database connected. Everything is fine:)");

        return dataSource;
    }

    public void initializeMemoryData(){
        orderDao = OrderDaoMem.getInstance();
        productCategoryDao = ProductCategoryDaoMem.getInstance();
        productDao = ProductDaoMem.getInstance();
        shoppingCartDao = ShoppingCartDaoMem.getInstance();
        supplierDao = SupplierDaoMem.getInstance();

        Supplier bkk = new Supplier("BKK");
        supplierDao.add(bkk);
        Supplier anonymous = new Supplier("Anonymous");
        supplierDao.add(anonymous);
        Supplier neo = new Supplier("Neo");
        supplierDao.add(neo);
        Supplier trinity = new Supplier("Trinity");
        supplierDao.add(trinity);
        Supplier d3f4ult = new Supplier("D3F4ULT");
        supplierDao.add(d3f4ult);
        Supplier theCalculator = new Supplier("The Calculator");
        supplierDao.add(theCalculator);
        Supplier mrRobot = new Supplier("Mr.Robot");
        supplierDao.add(mrRobot);
        Supplier m1ck3y = new Supplier("M1CK3Y");
        supplierDao.add(m1ck3y);

        //setting up a new product category
        ProductCategory hardware = new ProductCategory("Hardware");
        productCategoryDao.add(hardware);

        ProductCategory software = new ProductCategory("Software");
        productCategoryDao.add(software);

        ProductCategory merchandise = new ProductCategory("Merchandise");
        productCategoryDao.add(merchandise);

        ProductCategory stolenData = new ProductCategory("Stolen data");
        productCategoryDao.add(stolenData);

        ProductCategory service = new ProductCategory("Services");
        productCategoryDao.add(service);


        //setting up products and printing it


        productDao.add(new Product("Hacker Laptop", 949.1f, "USD", "hacker_laptop.jpg", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", hardware, anonymous));
        productDao.add(new Product("My first hacking laptop", 491, "USD", "kid_laptop.png", "Keep your baby's little hands (and mind) busy as can be with this interactive toy laptop that introduces password cracking, stealing data and more!", hardware, d3f4ult));
        productDao.add(new Product("Cracked curriculum", 89, "USD", "curriculum.png", "Finish Codecool in one day! All tasks with solutions", software, theCalculator));
        productDao.add(new Product("Cracking a password", 8.5f, "USD", "crack_password.jpg", "Cracking any password", service, theCalculator));
        productDao.add(new Product("Attend attendance", 2f, "USD", "attendance.jpg", "Attending attendance instead of you: being on time and great answer for attendance question included", service, trinity));
        productDao.add(new Product("Passing a PA", 20f, "USD", "PA.jpg", "We will take your PA for you, all green scores guaranteed!", service, mrRobot));
        productDao.add(new Product("Anonymous mask", 15.5f, "USD", "mask.jpg", "No one will know that you are a hacker if you wear this", merchandise, anonymous));
        productDao.add(new Product("Credit card credentials", 15000000f, "USD", "card.jpg", "Best credit card data with a lot of money", stolenData, m1ck3y));
        productDao.add(new Product("Hoodie", 150, "USD", "hoodie.jpg", "A must have for every real hacker", merchandise, neo));
        productDao.add(new Product("105 bus", 1, "USD", "105bus.jpg", "Bus to get you everywhere you want with your hacker friends. Bus connecting people", hardware, bkk));
    }
}
