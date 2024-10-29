import javax.swing.*;
import java.awt.*;

public class Animation {
    private static final Color HIGHLIGHT_COLOR = Color.YELLOW;
    private static final Color DEFAULT_COLOR = null;

    /**
     * Destaca o botão da última jogada e reseta a cor do botão anterior.
     * @param buttons Matriz de botões do tabuleiro.
     * @param lastMove Ponto da última jogada.
     * @param row Linha da jogada atual.
     * @param col Coluna da jogada atual.
     */
    public static void highlightButton(JButton[][] buttons, Point lastMove, int row, int col) {
        if (lastMove != null) {
            buttons[lastMove.x][lastMove.y].setBackground(DEFAULT_COLOR);
        }
        buttons[row][col].setBackground(HIGHLIGHT_COLOR);
    }

    /**
     * Exibe uma mensagem de fim de jogo.
     * @param frame JFrame da aplicação.
     * @param winner O jogador vencedor.
     * @param message Mensagem a ser exibida.
     */
    public static void showEndMessage(JFrame frame, Player winner, String message) {
        String finalMessage = (winner == Player.X) ? "Jogador " + Player.getPlayerName("X") + " venceu!" :
                              (winner == Player.O) ? "Jogador " + Player.getPlayerName("O") + " venceu!" : message;

        Object[] options = {"Jogar Novamente", "Voltar ao Menu Inicial", "Sair"};
        int choice = JOptionPane.showOptionDialog(frame, finalMessage, "Fim de Jogo",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == JOptionPane.YES_OPTION) {
            Main.resetBoard(); // Reinicia o jogo
        } else if (choice == JOptionPane.NO_OPTION) {
            new Menu(); // Volta ao menu inicial
            frame.dispose(); // Fecha o jogo atual
        } else {
            System.exit(0); // Sai do jogo
        }
    }
}
