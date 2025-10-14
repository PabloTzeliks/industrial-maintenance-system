package pablo.tzeliks.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/ims_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "passwd";

    public static Connection getConexao() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException ex) {

            throw new RuntimeException("Erro na conexão do Banco de Dados, observe: " + ex.getMessage());
        }
    }
}