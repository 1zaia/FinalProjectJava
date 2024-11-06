import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    private static final String URL = "jdbc:mysql://localhost:3306/game"; // URL para o MySQL
    private static final String USER = "root"; // Seu usuário MySQL
    private static final String PASSWORD = ""; // Sua senha MySQL

    static {
        try {
            // Registrar o driver manualmente
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Falha ao registrar o driver JDBC do MySQL", e);
        }
    }

    public static void createNewDatabase() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            if (conn != null) {
                Statement stmt = conn.createStatement();

                // Criar tabela players
                stmt.execute("CREATE TABLE IF NOT EXISTS players (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "name VARCHAR(255) NOT NULL," +
                        "wins INT NOT NULL DEFAULT 0" +
                        ");");

                System.out.println("Tabela criada com sucesso.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
