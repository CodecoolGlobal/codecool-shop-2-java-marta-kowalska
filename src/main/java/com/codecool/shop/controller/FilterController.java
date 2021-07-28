package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.product.Product;
import com.codecool.shop.service.ProductService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


@WebServlet(urlPatterns = {"/filter/"})
public class FilterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao productSupplierDataStore = SupplierDaoMem.getInstance();
        ShoppingCartDao shoppingCart = ShoppingCartDaoMem.getInstance();

        ProductService productService = new ProductService(productDataStore,productCategoryDataStore,productSupplierDataStore, shoppingCart);

        String category = req.getParameter("category").equals("") ? null : req.getParameter("category");
        String supplier = req.getParameter("supplier").equals("") ? null : req.getParameter("supplier");

        List<Product> foundProducts = new ArrayList<>();

        if(category!=null && supplier!=null){
            String[] allCategories = category.split(",");
            String[] allSuppliers = supplier.split(",");
            for (String c : allCategories) {
                for (String s : allSuppliers) {
                    foundProducts.addAll(productService.getProductsForCategoryAndSupplier(Integer.parseInt(c), Integer.parseInt(s)));
                }
            }
        } else if(category != null){
            String[] allCategories = category.split(",");
            for (String c : allCategories) {
                foundProducts.addAll(productService.getProductsForCategory(Integer.parseInt(c)));
            }
        } else if(supplier != null){
            String[] allSuppliers = supplier.split(",");
            for (String s : allSuppliers) {
                foundProducts.addAll(productService.getProductsForSupplier(Integer.parseInt(s)));
            }
        }

        String serializedProducts = NetworkUtil.jsonifyData(foundProducts);

        NetworkUtil.setResponseHeader(resp);
        NetworkUtil.sendJSONResponse(resp, serializedProducts);

    }

}
