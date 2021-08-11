package com.codecool.shop.config;

import com.codecool.shop.dao.implementation.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        setUpDatabase();
    }

    private void setUpDatabase() {
        LoadJDBCSettingsFromPropertiesFile properties = new LoadJDBCSettingsFromPropertiesFile();
        properties.load();
        }

}
