package com.codecool.shop.controller.filter;

import com.codecool.shop.model.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FilterUtil {

    public static int getRequestedId(HttpServletRequest req){
        // TODO make sure not int values are handled as well
        String path = req.getPathInfo();
        String[] parts = path.split("/");
        return Integer.parseInt(parts[1]);
    }

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
