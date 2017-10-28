package nl.han.oose.dea.jamielvanengen.datasources.daos;

import com.mysql.cj.jdbc.MysqlDataSource;
import nl.han.oose.dea.jamielvanengen.constants.DatabaseDrivers;

import javax.enterprise.inject.Default;
import javax.sql.DataSource;
import javax.ws.rs.NotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

@Default
public class ConnectionFactory {
    public Connection getConnectionFromProperties() {
        switch (getDriverFromProperties()) {
            case MYSQL:
            default:
                return getMySqlConnection();
        }
    }

    private Connection getMySqlConnection() {
        MysqlDataSource dataSource = new MysqlDataSource();
        Properties properties = getProperties();
        dataSource.setServerName(properties.getProperty("serverName"));
        dataSource.setDatabaseName(properties.getProperty("databaseName"));
        try {
            return dataSource.getConnection(properties.getProperty("dbUserId"), properties.getProperty("dbPass"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private DatabaseDrivers getDriverFromProperties() {
        Properties properties = getProperties();
        return DatabaseDrivers.getDatabaseDriverFrom(properties.getProperty("database-driver").toUpperCase());
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
