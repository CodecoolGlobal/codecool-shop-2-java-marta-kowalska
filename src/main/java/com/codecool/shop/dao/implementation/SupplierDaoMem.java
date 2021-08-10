package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.product.Supplier;

import javax.sql.DataSource;
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
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
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
