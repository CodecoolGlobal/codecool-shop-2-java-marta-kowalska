package com.codecool.shop.dao.implementation.JDBC;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.product.Product;
import com.codecool.shop.model.product.ProductCategory;
import com.codecool.shop.model.product.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {
    private static DataSource dataSource;

    private List<Product> data = new ArrayList<>();
    private static ProductDaoJdbc instance = null;
    private Logger logger = LoggerFactory.getLogger(ShoppingCartDaoJdbc.class);
    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoJdbc(DataSource dataSource) {
        ProductDaoJdbc.dataSource = dataSource;
    }

    public static ProductDaoJdbc getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new ProductDaoJdbc(dataSource);
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        product.setId(data.size() + 1);
        data.add(product);
    }

    @Override
    public Product find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, description, price, currency, picture, category_id, supplier_id FROM product WHERE id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return null;
            }
            return new Product(rs.getInt("ID"),
                rs.getString("NAME"),
                rs.getString("DESCRIPTION"),
                rs.getFloat("PRICE"),
                rs.getString("CURRENCY"),
                rs.getString("PICTURE"),
                rs.getInt("CATEGORY_ID"),
                rs.getInt("SUPPLIER_ID")
                );
        } catch(SQLException e){
            logger.error("Sql Error" + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<Product> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            List<Product> allProducts = new ArrayList<>();
            String sql = "SELECT id, name, description, price, currency, picture, category_id, supplier_id FROM product";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Product product = new Product(rs.getInt("ID"),
                    rs.getString("NAME"),
                    rs.getString("DESCRIPTION"),
                    rs.getFloat("PRICE"),
                    rs.getString("CURRENCY"),
                    rs.getString("PICTURE"),
                    rs.getInt("CATEGORY_ID"),
                    rs.getInt("SUPPLIER_ID")
                );
                allProducts.add(product);
            }
            return allProducts;
        } catch(SQLException e){
            logger.error("Sql Error" + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        int supplierId = supplier.getId();
        try (Connection conn = dataSource.getConnection()) {
            List<Product> productsBySupplier = new ArrayList<>();
            String sql = "SELECT id, name, description, price, currency, picture, category_id, supplier_id FROM product WHERE supplier_id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, supplierId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Product product = new Product(rs.getInt("ID"),
                    rs.getString("NAME"),
                    rs.getString("DESCRIPTION"),
                    rs.getFloat("PRICE"),
                    rs.getString("CURRENCY"),
                    rs.getString("PICTURE"),
                    rs.getInt("CATEGORY_ID"),
                    rs.getInt("SUPPLIER_ID")
                );
                productsBySupplier.add(product);
            }
            return productsBySupplier;
        } catch(SQLException e){
            logger.error("Sql Error" + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        int productCategoryId = productCategory.getId();
        try (Connection conn = dataSource.getConnection()) {
            List<Product> productsBySupplier = new ArrayList<>();
            String sql = "SELECT id, name, description, price, currency, picture, category_id, supplier_id FROM product WHERE category_id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, productCategoryId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Product product = new Product(rs.getInt("ID"),
                    rs.getString("NAME"),
                    rs.getString("DESCRIPTION"),
                    rs.getFloat("PRICE"),
                    rs.getString("CURRENCY"),
                    rs.getString("PICTURE"),
                    rs.getInt("CATEGORY_ID"),
                    rs.getInt("SUPPLIER_ID")
                );
                productsBySupplier.add(product);
            }
            return productsBySupplier;
        } catch(SQLException e){
            logger.error("Sql Error" + e);
            throw new RuntimeException(e);
        }
    }

    public List<Product> getBy(ProductCategory productCategory, Supplier supplier) {
        int productCategoryId = productCategory.getId();
        int supplierId = supplier.getId();
        try (Connection conn = dataSource.getConnection()) {
            List<Product> productsBySupplier = new ArrayList<>();
            String sql = "SELECT id, name, description, price, currency, picture, category_id, supplier_id FROM product WHERE category_id=? AND supplier_id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, productCategoryId);
            statement.setInt(2, supplierId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Product product = new Product(rs.getInt("ID"),
                    rs.getString("NAME"),
                    rs.getString("DESCRIPTION"),
                    rs.getFloat("PRICE"),
                    rs.getString("CURRENCY"),
                    rs.getString("PICTURE"),
                    rs.getInt("CATEGORY_ID"),
                    rs.getInt("SUPPLIER_ID")
                );
                productsBySupplier.add(product);
            }
            return productsBySupplier;
        } catch(SQLException e){
            logger.error("Sql Error" + e);
            throw new RuntimeException(e);
        }
    }
}
