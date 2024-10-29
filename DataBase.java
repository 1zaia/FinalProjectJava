import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    private static final String URL = "jdbc:sqlite:game.db";

    public static void createNewDatabase() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                stmt.execute("CREATE TABLE IF NOT EXISTS players (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT NOT NULL," +
                        "wins INTEGER NOT NULL DEFAULT 0" +
                        ");");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
