package com.codecool.shop.controller;

import com.codecool.shop.model.product.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class NetworkUtil {

    public static String jsonifyData(List<Product> productsByCategory) {
        Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setPrettyPrinting()
            .create();

        return gson.toJson(productsByCategory);
    }

    public static void setResponseHeader(HttpServletResponse resp){
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
    }

    public static void sendJSONResponse(HttpServletResponse resp, String serializedProducts) throws IOException {
        PrintWriter out = resp.getWriter();
        out.write(serializedProducts);
        out.flush();
    }

}