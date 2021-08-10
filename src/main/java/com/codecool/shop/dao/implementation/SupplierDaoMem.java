package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.product.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoMem implements SupplierDao {
    private static DataSource dataSource;
    private List<Supplier> data = new ArrayList<>();
    private static SupplierDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private SupplierDaoMem(DataSource dataSource) {
        SupplierDaoMem.dataSource = dataSource;
    }


    public static SupplierDaoMem getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new SupplierDaoMem(dataSource);
        }
        return instance;
    }

//    @Override
//    public void add(Supplier supplier) {
//        supplier.setId(data.size() + 1);
//        data.add(supplier);
//    }

    @Override
    public Supplier find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name FROM product_supplier WHERE id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return null;
            }
            Supplier supplier = new Supplier(rs.getString(1));
            supplier.setId(id);
            return supplier;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public void remove(int id) {
//        data.remove(find(id));
//    }

    @Override
    public List<Supplier> getAll() {
        return data;
    }
}
