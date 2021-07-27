package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/get-supplier/*"})
public class SupplierFilter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        int categoryId = FilterUtil.getRequestedId(req);

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao productSupplierDataStore = SupplierDaoMem.getInstance();

        ProductService productService = new ProductService(productDataStore,productCategoryDataStore,productSupplierDataStore);

        List<Product> productsByCategory = productService.getProductsForSupplier(categoryId);

        String serializedProducts = FilterUtil.jsonifyData(productsByCategory);

        FilterUtil.setResponseHeader(resp);
        FilterUtil.sendJSONResponse(resp, serializedProducts);

    }

}
