package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.product.Product;
import com.codecool.shop.model.product.ProductCategory;
import com.codecool.shop.model.product.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDaoMem implements ProductDao {
    private static DataSource dataSource;

    private List<Product> data = new ArrayList<>();
    private static ProductDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoMem(DataSource dataSource) {
        ProductDaoMem.dataSource = dataSource;
    }

    public static ProductDaoMem getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new ProductDaoMem(dataSource);
        }
        return instance;
    }

//    @Override
//    public void add(Product product) {
//        product.setId(data.size() + 1);
//        data.add(product);
//    }

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
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public void remove(int id) {
//        data.remove(find(id));
//    }

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
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
//        return data.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
//        return data.stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
    return null;
    }

    public List<Product> getBy(ProductCategory productCategory, Supplier supplier) {
//        return data.stream()
//            .filter(t -> t.getProductCategory().equals(productCategory))
//            .filter(t -> t.getSupplier().equals(supplier))
//            .collect(Collectors.toList());
        return null;
    }
}
