import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:sqlite:banco.db";
    // Para outros bancos, ajuste o URL, usuário e senha
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
} 