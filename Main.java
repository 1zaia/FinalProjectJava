import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static JFrame frame;
    private static JButton[][] buttons = new JButton[3][3];
    private static GameBoard board;
    private static boolean playerTurn = true; // True = Jogador X, False = Jogador O
    private static int gameMode = 0; // 1 = Jogador vs Jogador, 2 = IA Fácil, 3 = IA Difícil
    private static GameStatus gameStatus;
    private String playerXName;
    private String playerOName;

    public Main() {
        frame = new JFrame("Jogo da Velha");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // Centraliza a janela
    }

    public void initializeGame(String playerX, String playerO) {
        this.playerXName = playerX;
        this.playerOName = playerO;
        gameStatus = new GameStatus(playerXName, playerOName);
        initializeBoard();
        frame.add(gameStatus.getPanel(), BorderLayout.NORTH);
        frame.setVisible(true);
    }

    public static void setGameMode(int mode) {
        gameMode = mode;
    }

    private void initializeBoard() {
        board = new GameBoard();
        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        boardPanel.setBackground(new Color(50, 50, 50)); // Fundo escuro para contraste
        
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.BOLD, 150)); // Fonte maior e em negrito
                buttons[row][col].setForeground(new Color(255, 255, 255)); // Texto branco
                buttons[row][col].setBackground(new Color(70, 70, 70)); // Fundo dos botões escuro
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 5)); // Bordas dos botões
                final int currentRow = row;
                final int currentCol = col;
                buttons[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (board.isEmpty(currentRow, currentCol)) {
                            makeMove(currentRow, currentCol);
                        }
                    }
                });
                boardPanel.add(buttons[row][col]);
            }
        }
        frame.add(boardPanel, BorderLayout.CENTER);
        
        // Adiciona um painel inferior com informações do jogo
        JPanel infoPanel = new JPanel();
        JLabel modeLabel = new JLabel(getModeText());
        modeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        infoPanel.add(modeLabel);
        frame.add(infoPanel, BorderLayout.SOUTH);
    }

    private void makeMove(int row, int col) {
        if (playerTurn) {
            buttons[row][col].setText("X");
            buttons[row][col].setBackground(new Color(200, 70, 70));
            board.setMove(row, col, Player.X);
        } else {
            buttons[row][col].setText("O");
            buttons[row][col].setBackground(new Color(70, 70, 200));
            board.setMove(row, col, Player.O);
        }
        
        playerTurn = !playerTurn;
        gameStatus.updateStatus(playerTurn);
        checkGameStatus();

        // Se for modo contra computador e for vez do computador
        if (gameMode != 1 && !playerTurn) {
            // Pequeno delay para melhor visualização
            Timer timer = new Timer(500, e -> computerMove());
            timer.setRepeats(false);
            timer.start();
        }
    }

    private String getModeText() {
        switch (gameMode) {
            case 1:
                return "Modo: Jogador vs Jogador";
            case 2:
                return "Modo: Jogador vs IA (Fácil)";
            case 3:
                return "Modo: Jogador vs IA (Difícil)";
            default:
                return "Modo não selecionado";
        }
    }

    private void computerMove() {
        if (!playerTurn) {
            if (gameMode == 2) {
                ComputerPlayer.makeMoveEasy(board.getBoard());
            } else if (gameMode == 3) {
                ComputerPlayer.makeMoveHard(board.getBoard());
            }
            updateBoard();
            playerTurn = true;
            gameStatus.updateStatus(playerTurn);
            checkGameStatus();
        }
    }

    private void updateBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board.getMove(row, col) == Player.O && buttons[row][col].getText().isEmpty()) {
                    buttons[row][col].setText("O");
                    buttons[row][col].setBackground(new Color(70, 70, 200));
                }
            }
        }
    }

    private void checkGameStatus() {
        Player winner = board.checkWinner();
        if (winner != Player.NONE) {
            gameStatus.updateScore(winner);
            String winnerName = (winner == Player.X) ? playerXName : playerOName;
            Animation.showEndMessage(frame, winner, "Fim do Jogo! " + winnerName + " venceu!");
        } else if (board.isFull()) {
            Animation.showEndMessage(frame, Player.NONE, "Empate!");
        }
    }

    public static void resetBoard() {
        board.reset();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
                buttons[row][col].setBackground(new Color(70, 70, 70));
                buttons[row][col].setEnabled(true);
            }
        }
        playerTurn = true;
        gameStatus.updateStatus(playerTurn);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Menu());
    }
}
