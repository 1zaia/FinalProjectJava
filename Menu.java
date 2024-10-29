import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu {
    private JFrame frame;
    private Color defaultButtonColor = new Color(70, 130, 180);
    private Color hoverButtonColor = new Color(100, 149, 237);
    private Font titleFont = new Font("Arial", Font.BOLD, 50);
    private Font buttonFont = new Font("Arial", Font.BOLD, 24);

    public Menu() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("Jogo da Velha");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        // Painel principal com layout BoxLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(50, 50, 50));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        // Título do jogo
        JLabel titleLabel = new JLabel("Jogo da Velha");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        // Espaçamento após o título
        mainPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        // Botões do menu
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(50, 50, 50));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Criar botões
        JButton pvpButton = createMenuButton("Jogador vs Jogador");
        JButton pveEasyButton = createMenuButton("Jogador vs IA (Fácil)");
        JButton pveHardButton = createMenuButton("Jogador vs IA (Difícil)");
        JButton exitButton = createMenuButton("Sair");
        // Adicionar ações aos botões
        pvpButton.addActionListener(e -> initializePVP());
        pveEasyButton.addActionListener(e -> initializePVE(2));
        pveHardButton.addActionListener(e -> initializePVE(3));
        exitButton.addActionListener(e -> System.exit(0));
        // Adicionar botões ao painel
        buttonPanel.add(pvpButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(pveEasyButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(pveHardButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(exitButton);
        mainPanel.add(buttonPanel);
        // Adicionar créditos na parte inferior
        JLabel creditsLabel = new JLabel("Desenvolvido por Juan Alberdi");
        creditsLabel.setFont(new Font("Arial", Font.PLAIN, 9));
        creditsLabel.setForeground(new Color(200, 200, 200));
        creditsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(creditsLabel);
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(buttonFont);
        button.setForeground(Color.WHITE);
        button.setBackground(defaultButtonColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(400, 60));
        button.setPreferredSize(new Dimension(400, 60));
        // Adicionar efeitos hover
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverButtonColor);
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(defaultButtonColor);
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        return button;
    }

    private void initializePVP() {
        frame.dispose();
        Main.setGameMode(1);
        SwingUtilities.invokeLater(() -> new PlayerRegistration(1));
    }

    private void initializePVE(int difficulty) {
        frame.dispose();
        Main.setGameMode(difficulty);
        SwingUtilities.invokeLater(() -> new PlayerRegistration(difficulty));
    }

    // Método estático para reiniciar o menu
    public static void restartMenu() {
        SwingUtilities.invokeLater(() -> new Menu());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Menu());
    }
}
