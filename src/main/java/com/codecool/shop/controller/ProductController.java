package com.codecool.shop.controller;

import com.codecool.shop.controller.filter.FilterUtil;
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
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao productSupplierDataStore = SupplierDaoMem.getInstance();
        ShoppingCartDao shoppingCart = ShoppingCartDaoMem.getInstance();

        ProductService productService = new ProductService(productDataStore,productCategoryDataStore,productSupplierDataStore, shoppingCart);

        String category = req.getParameter("category");
        String supplier = req.getParameter("supplier");

        List<Product> foundProducts = new ArrayList<>();

        //set default products if no params were found

        // if params were found, set products according to search below

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

        String serializedProducts = FilterUtil.jsonifyData(foundProducts);

        FilterUtil.setResponseHeader(resp);
        FilterUtil.sendJSONResponse(resp, serializedProducts);

//        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
//        WebContext context = new WebContext(req, resp, req.getServletContext());
//        context.setVariable("category", productService.getProductCategory(1));
//
//        context.setVariable("categories", productService.getAllCategory());
//
//        context.setVariable("suppliers", productService.getProductsForSupplier(1));
//
//        context.setVariable("products", productService.getProductsForCategory(1));
//        // // Alternative setting of the template context
//        // Map<String, Object> params = new HashMap<>();
//        // params.put("category", productCategoryDataStore.find(1));
//        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
//        // context.setVariables(params);
//        engine.process("product/index.html", context, resp.getWriter());
    }

}
