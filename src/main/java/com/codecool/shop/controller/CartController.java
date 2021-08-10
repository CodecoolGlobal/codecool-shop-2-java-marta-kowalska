package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.DatabaseManager;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
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

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    DatabaseManager dbManager = DatabaseManager.getInstance();
    ProductDao productDataStore = dbManager.getProductDao();
    ShoppingCartDao shoppingCart = dbManager.getShoppingCartDao();

    ProductService productService = new ProductService(productDataStore, shoppingCart);


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer productId = Integer.valueOf(req.getParameter("productId"));
        String action = req.getParameter("action");
        switch (action){
            case "add":
                productService.getShoppingCart().addToShoppingCart(productDataStore.find(productId));
                break;
            case "remove":
                productService.getShoppingCart().removeOneItemFromShoppingCart(productDataStore.find(productId));
                break;
            case "delete":
                productService.getShoppingCart().removeItemFromShoppingCart(productDataStore.find(productId));
                break;
        }

        resp.setStatus(200);
        resp.getWriter().write("ok");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("cart", productService.getAllCartItems());

        context.setVariable("summary", productService.getShoppingCart().getShoppingCartSummary());

        var miniCartParameter = req.getParameter("minicart");
        boolean isMiniCart = miniCartParameter != null && Integer.parseInt(miniCartParameter) == 1;

        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
        engine.process(isMiniCart ? "product/minicart.html" : "product/cart.html", context, resp.getWriter());
    }

}