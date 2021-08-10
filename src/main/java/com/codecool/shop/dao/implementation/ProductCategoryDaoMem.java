package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.product.ProductCategory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoMem implements ProductCategoryDao {
    private static DataSource dataSource;

    private static ProductCategoryDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductCategoryDaoMem(DataSource dataSource) {
        ProductCategoryDaoMem.dataSource = dataSource;
    }

    public static ProductCategoryDaoMem getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new ProductCategoryDaoMem(dataSource);
        }
        return instance;
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
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<ProductCategory> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            List<ProductCategory> allCategories = new ArrayList<>();
            String sql = "SELECT id, name FROM product_category";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
//                System.out.println(rs.getString("NAME"));
                ProductCategory category = new ProductCategory(rs.getString("NAME"));
                category.setId(rs.getInt("ID"));
                allCategories.add(category);
            }
            return allCategories;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
