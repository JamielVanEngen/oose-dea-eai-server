package nl.han.oose.dea.jamielvanengen.datasources.daos;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.ejb.CreateException;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public abstract class DAO {
    private Connection connection;

    private String serverName, databaseName, dbUserId, dbPass;

    protected Connection getConnection() throws SQLException {
        Properties properties = getProperties();
        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName(properties.getProperty("serverName"));
        ds.setDatabaseName(properties.getProperty("databaseName"));
        return ds.getConnection(properties.getProperty("dbUserId"), properties.getProperty("dbPass"));
    }

    private Properties getProperties() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("application.properties");
        Properties properties = new Properties();
        try {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
