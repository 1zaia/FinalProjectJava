import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GameStatus {
    private static final String TURN_X = "Turno: %s";
    private static final String TURN_O = "Turno: %s";
    private static final String SCORE_TEMPLATE = "Placar - %s: %d | %s: %d";
    private static final String URL = "jdbc:sqlite:game.db";
    private String playerXName;
    private String playerOName;
    private int scoreX = 0;
    private int scoreO = 0;
    private JLabel statusLabel;
    private JLabel scoreLabel;
    private JPanel panel;

    public GameStatus(String playerXName, String playerOName) {
        this.playerXName = playerXName;
        this.playerOName = playerOName;

        statusLabel = new JLabel(String.format(TURN_X, playerXName));
        statusLabel.setFont(new Font("Arial", Font.BOLD, 40));
        scoreLabel = new JLabel(String.format(SCORE_TEMPLATE, playerXName, scoreX, playerOName, scoreO));
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 40));
        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(statusLabel);
        panel.add(scoreLabel);

        DataBase.createNewDatabase();
    }

    public JPanel getPanel() {
        return panel;
    }

    public void updateStatus(boolean isPlayerXTurn) {
        statusLabel.setText(isPlayerXTurn ?
                String.format(TURN_X, playerXName) :
                String.format(TURN_O, playerOName));
    }

    public void updateScore(Player winner) {
        if (winner == Player.X) {
            scoreX++;
            updateWinsInDatabase(playerXName);
        } else if (winner == Player.O) {
            scoreO++;
            updateWinsInDatabase(playerOName);
        }
        scoreLabel.setText(String.format(SCORE_TEMPLATE, playerXName, scoreX, playerOName, scoreO));
    }

    private void updateWinsInDatabase(String playerName) {
        String sql = "INSERT INTO players (name, wins) VALUES (?, 1) " +
                     "ON CONFLICT(name) DO UPDATE SET wins = wins + 1";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, playerName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
