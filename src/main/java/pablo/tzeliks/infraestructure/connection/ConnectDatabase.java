package pablo.tzeliks.infraestructure.connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectDatabase {

    private static final String URL = "";
    private static final String USER = "";
    private static final String PASSWD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWD);
    }
}