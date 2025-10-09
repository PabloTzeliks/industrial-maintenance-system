package pablo.tzeliks.infraestructure.connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectDatabase {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/?user=root";
    private static final String USER = "root";
    private static final String PASSWD = "passwd";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWD);
    }
}