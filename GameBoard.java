public class GameBoard {
    private Player[][] board;

    public GameBoard() {
        board = new Player[3][3];
        reset();
    }

    public void reset() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = Player.NONE;
            }
        }
    }

    public boolean isEmpty(int row, int col) {
        return board[row][col] == Player.NONE;
    }

    public void setMove(int row, int col, Player player) {
        board[row][col] = player;
    }

    public Player getMove(int row, int col) {
        return board[row][col];
    }

    public boolean isFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == Player.NONE) {
                    return false;
                }
            }
        }
        return true;
    }

    public Player checkWinner() {
        // Verifica linhas e colunas
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != Player.NONE) {
                return board[i][0];
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != Player.NONE) {
                return board[0][i];
            }
        }
        // Verifica diagonais
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != Player.NONE) {
            return board[0][0];
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != Player.NONE) {
            return board[0][2];
        }
        return Player.NONE;
    }

    public Player[][] getBoard() {
        return board;
    }
}
