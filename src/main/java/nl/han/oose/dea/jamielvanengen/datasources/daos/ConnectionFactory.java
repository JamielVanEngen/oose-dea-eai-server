package nl.han.oose.dea.jamielvanengen.datasources.daos;

import com.mysql.cj.jdbc.MysqlDataSource;
import nl.han.oose.dea.jamielvanengen.constants.DatabaseDrivers;

import javax.sql.DataSource;
import javax.ws.rs.NotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataSourceFactory {
    public DataSource getDatasourceFromProperties() {
        switch (getDriverFromProperties()) {
            case MYSQL:
                return new MysqlDataSource();

            case MSSQL:
                
            default:
                return new MysqlDataSource();
        }
    }

    private DatabaseDrivers getDriverFromProperties() {
        Properties properties = getProperties();
        return DatabaseDrivers.valueOf(properties.getProperty("database-driver"));
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
