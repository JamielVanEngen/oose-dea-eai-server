package nl.han.oose.dea.jamielvanengen.datasources.daos;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public abstract class DAO {
    @Inject
    protected ConnectionFactory connectionFactory;
}
