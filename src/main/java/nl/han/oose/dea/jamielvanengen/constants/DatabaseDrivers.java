package nl.han.oose.dea.jamielvanengen.constants;

public enum DatabaseDrivers {
    MYSQL, MSSQL;

    public static DatabaseDrivers getDatabaseDriverFrom(String driver) {
        try {
            return valueOf(driver);
        }
        catch (IllegalArgumentException ex) {
            return DatabaseDrivers.MSSQL;
        }
    }
}
