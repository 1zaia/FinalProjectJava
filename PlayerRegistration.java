import javax.swing.*;
import java.awt.*;

public class PlayerRegistration {
    private JFrame frame;
    private JTextField nameField;
    private JButton registerButton;
    private int gameMode;
    private boolean isFirstPlayer = true;
    private String firstPlayerName;

    public PlayerRegistration(int gameMode) {
        this.gameMode = gameMode;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("Registro de Jogador");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(50, 50, 50));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBackground(new Color(50, 50, 50));
        JLabel nameLabel = new JLabel("Nome:");
        nameLabel.setForeground(Color.WHITE);
        nameField = new JTextField(10); // Tamanho da caixa de texto reduzido
        nameField.setBackground(new Color(70, 70, 70));
        nameField.setForeground(Color.WHITE);
        nameField.setCaretColor(Color.WHITE);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        registerButton = new JButton("Registrar");
        styleButton(registerButton);
        registerButton.addActionListener(e -> registerPlayer());
        mainPanel.add(formPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(registerButton);
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void registerPlayer() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            showError("Por favor, preencha todos os campos!");
            return;
        }

        if (isFirstPlayer) {
            firstPlayerName = name;
            nameField.setText("");  // Limpar o campo para o próximo jogador
            if (gameMode == 1) {
                JOptionPane.showMessageDialog(frame, "Agora, registre o segundo jogador.");
                isFirstPlayer = false;
            } else {
                Player.setPlayerName(Player.X, firstPlayerName);
                Player.setPlayerName(Player.O, "Computador");
                startGame();
            }
        } else {
            Player.setPlayerName(Player.X, firstPlayerName);
            Player.setPlayerName(Player.O, name);
            startGame();
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void startGame() {
        frame.dispose();
        new Main().initializeGame(Player.getPlayerName("X"), Player.getPlayerName("O"));
    }
}
