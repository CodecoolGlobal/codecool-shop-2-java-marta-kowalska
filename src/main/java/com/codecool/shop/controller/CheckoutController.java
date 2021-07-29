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
    ShoppingCartDao shoppingCart = ShoppingCartDaoMem.getInstance();
    OrderDao orderDao = OrderDaoMem.getInstance();

    ProductService productService = new ProductService(shoppingCart, orderDao);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("summary", productService.getShoppingCart().getShoppingCartSummary());

        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
        engine.process("product/checkout.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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

        String billingCountry = req.getParameter("b-country");
        String billingCity = req.getParameter("b-city");
        String billingZipcode = req.getParameter("b-zipcode");
        String billingAddress = req.getParameter("b-address");

        Order order = new Order(firstName, lastName, email, phoneNumber, country, city, zipcode, address);
        orderDao.add(order);

        context.setVariable("id", productService.getOrderId(order));
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
