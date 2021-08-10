package com.codecool.shop.config;

import com.codecool.shop.dao.implementation.*;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

@WebListener
public class Initializer implements ServletContextListener {

    private DatabaseManager dbManager;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        setupDbManager();
    }

    private void setupDbManager () {
        dbManager = new DatabaseManager();
        try {
            dbManager.setup();
        } catch (SQLException ex) {
            System.out.println("Cannot connect to database.");
            System.out.println(ex.getMessage());
        }
    }
}
