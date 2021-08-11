package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.Order;
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
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

    DatabaseManager dbManager = DatabaseManager.getInstance();
    ShoppingCartDao shoppingCart = dbManager.getShoppingCartDao();
    OrderDao orderDao = dbManager.getOrderDao();

    ProductService productService = new ProductService(shoppingCart, orderDao);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("summary", productService.getShoppingCart().getShoppingCartSummary());

        engine.process("product/checkout.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String firstName = req.getParameter("fname");
        String lastName = req.getParameter("lname");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phone");
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        String zipcode = req.getParameter("zipcode");
        String address = req.getParameter("address");

        int orderId = orderDao.add(firstName, lastName, email, phoneNumber, country, city, zipcode, address);

        context.setVariable("id", orderId);
        context.setVariable("fname", firstName);
        context.setVariable("lname", lastName);
        context.setVariable("email", email);
        context.setVariable("phone", phoneNumber);
        context.setVariable("country", country);
        context.setVariable("city", city);
        context.setVariable("zipcode", zipcode);
        context.setVariable("address", address);

        context.setVariable("cart", productService.getAllCartItems());
        context.setVariable("summary", productService.getShoppingCart().getShoppingCartSummary());

        shoppingCart.deleteCart();

        engine.process("/confirmed.html", context, resp.getWriter());
    }

}
