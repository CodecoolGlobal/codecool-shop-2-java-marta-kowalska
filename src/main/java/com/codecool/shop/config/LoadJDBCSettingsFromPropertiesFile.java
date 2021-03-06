package com.codecool.shop.config;


import com.codecool.shop.dao.implementation.DatabaseManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.Properties;

public class LoadJDBCSettingsFromPropertiesFile {

    private static Logger logger = LoggerFactory.getLogger(LoadJDBCSettingsFromPropertiesFile.class);
    private DatabaseManager dbManager;

    public void load() {
        try {
            // Create Properties object.
            Properties props = new Properties();

            String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            String appConfigPath = rootPath + "config.properties";

            // Properties will use a FileReader object as input.
            FileReader fReader = new FileReader(appConfigPath);

            // Load jdbc related properties in above file.
            props.load(fReader);

            // Get each property value.
            String dbDriverClass = props.getProperty("db.driver.class");
            String dbConnUrl = props.getProperty("db.conn.url");
            int dbConnPort = Integer.parseInt(props.getProperty("db.conn.port"));
            String dbName = props.getProperty("db.dbname");
            String dbUserName = props.getProperty("db.username");
            String dbPassword = props.getProperty("db.password");
            if (dbPassword.equals("environmentVariable")){
                dbPassword = System.getenv("password");
            }
            if (dbUserName.equals("environmentVariable")){
                dbUserName = System.getenv("username");
            }
            String dao = props.getProperty("dao");

            if (!"".equals(dbDriverClass) && !"".equals(dbConnUrl)) {
                dbManager = DatabaseManager.getInstance();
                if (dao.equals("memory")) {
                    dbManager.initializeMemoryData();
                }else if(dao.equals("database")){
                    tryToConnenctDb(dbConnUrl, dbConnPort,dbName, dbUserName, dbPassword);
                } else {
                    tryToConnenctDb(dbConnUrl, dbConnPort,dbName, dbUserName, dbPassword);
                }
            }

        } catch (FileNotFoundException err) {
            logger.error("Error " + err);
            err.printStackTrace();
        } catch (Exception ex) {
            logger.error("Error " + ex);
            ex.printStackTrace();
        }
    }

    private void tryToConnenctDb(String url, int port, String dbName, String dbUserName, String dbPassword) {
        try {
            dbManager.setup(url, port,dbName, dbUserName, dbPassword);
        } catch (SQLException ex) {
            System.out.println("Cannot connect to database.");
            System.out.println(ex.getMessage());
            logger.error("Cannot connect to database, switched to memory." + ex);
            dbManager.initializeMemoryData();
        }
    }
}

