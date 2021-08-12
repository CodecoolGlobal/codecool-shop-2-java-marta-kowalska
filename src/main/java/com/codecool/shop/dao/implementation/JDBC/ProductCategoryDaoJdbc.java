package com.codecool.shop.dao.implementation.JDBC;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.product.ProductCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductCategoryDaoJdbc implements ProductCategoryDao {
    private static DataSource dataSource;

    private static ProductCategoryDaoJdbc instance = null;
    private Logger logger = LoggerFactory.getLogger(ShoppingCartDaoJdbc.class);
    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductCategoryDaoJdbc(DataSource dataSource) {
        ProductCategoryDaoJdbc.dataSource = dataSource;
    }

    public static ProductCategoryDaoJdbc getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new ProductCategoryDaoJdbc(dataSource);
        }
        return instance;
    }


    @Override
    public void add(ProductCategory category) {

    }

    @Override
    public ProductCategory find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name FROM product_category WHERE id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return null;
            }
            ProductCategory productCategory = new ProductCategory(rs.getString(1));
            productCategory.setId(id);
            return productCategory;
        } catch(SQLException e){
            logger.error("Sql Error" + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {

    }


    @Override
    public List<ProductCategory> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            List<ProductCategory> allCategories = new ArrayList<>();
            String sql = "SELECT id, name FROM product_category";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                ProductCategory category = new ProductCategory(rs.getString("NAME"));
                category.setId(rs.getInt("ID"));
                allCategories.add(category);
            }
            return allCategories;
        } catch(SQLException e){
            logger.error("Sql Error" + e);
            throw new RuntimeException(e);
        }
    }
}
