package com.codecool.shop.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class LoadJDBCSettingsFromPropertiesFile {

    private static Logger logger = LoggerFactory.getLogger(LoadJDBCSettingsFromPropertiesFile.class);

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
            String dbName = props.getProperty("db.dbname");
            String dbUserName = props.getProperty("db.username");
            String dbPassword = props.getProperty("db.password");
            System.out.printf(" %s  %s  %s %n ",dbUserName, dbPassword , dbName);


//            if (!"".equals(dbDriverClass) && !"".equals(dbConnUrl)) {
//                /* Register jdbc driver class. */
//                Class.forName(dbDriverClass);
//
//                // Get database connection object.
//                Connection dbConn = DriverManager.getConnection(dbConnUrl, dbUserName, dbPassword);
//
//                // Get dtabase meta data.
//                DatabaseMetaData dbMetaData = dbConn.getMetaData();
//
//                // Get database name.
//                String dbName = dbMetaData.getDatabaseProductName();
//
//                // Get database version.
//                String dbVersion = dbMetaData.getDatabaseProductVersion();
//        }

//            public void setup() throws SQLException {
//                DataSource dataSource = connect();
//            }
//
//            private DataSource connect() throws SQLException {
//                PGSimpleDataSource dataSource = new PGSimpleDataSource();
//                String dbName = dbPassword;
//                String user = dbUserName
//                String password = dbPassword;
//
//                dataSource.setDatabaseName(dbName);
//                dataSource.setUser(user);
//                dataSource.setPassword(password);
//
//                System.out.println("Trying to connect");
//                dataSource.getConnection().close();
//                System.out.println("Connection ok.");
//                return dataSource;
//            }


//            }else{
//            /* use classes as Database Objects. */
//            }

        } catch (FileNotFoundException err) {
            logger.error("Error " +err);
            err.printStackTrace();
        } catch (Exception ex) {
            logger.error("Error " + ex);
            ex.printStackTrace();
        }
    }
}

