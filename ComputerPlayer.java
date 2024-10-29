public class ComputerPlayer {
    // Jogada fácil (movimento aleatório)
    public static void makeMoveEasy(Player[][] game) {
        boolean moveMade = false;
        while (!moveMade) {
            int row = (int) (Math.random() * 3);
            int col = (int) (Math.random() * 3);
            if (game[row][col] == Player.NONE) {
                game[row][col] = Player.O;
                moveMade = true;
            }
        }
    }

    // Jogada difícil (usando Minimax)
    public static void makeMoveHard(Player[][] game) {
        int[] bestMove = minimax(game, Player.O);
        game[bestMove[0]][bestMove[1]] = Player.O;
    }

    // Função Minimax para calcular a melhor jogada
    private static int[] minimax(Player[][] board, Player player) {
        int bestScore = (player == Player.O) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int currentScore;
        int[] bestMove = {-1, -1};
        if (isGameOver(board)) {
            return new int[]{-1, -1, evaluateBoard(board)};
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == Player.NONE) {
                    board[i][j] = player;
                    currentScore = (player == Player.O) ? minimax(board, Player.X)[2] : minimax(board, Player.O)[2];
                    if ((player == Player.O && currentScore > bestScore) || (player == Player.X && currentScore < bestScore)) {
                        bestScore = currentScore;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                    board[i][j] = Player.NONE; // Desfaz a jogada provisória
                }
            }
        }
        return new int[]{bestMove[0], bestMove[1], bestScore};
    }

    // Função que avalia o estado do tabuleiro (pontuação)
    private static int evaluateBoard(Player[][] board) {
        Player winner = checkWinner(board);
        if (winner == Player.O) {
            return 10; // Vitória do computador
        } else if (winner == Player.X) {
            return -10; // Vitória do jogador humano
        } else {
            return 0; // Empate
        }
    }

    // Função que verifica se o jogo terminou
    private static boolean isGameOver(Player[][] board) {
        return checkWinner(board) != Player.NONE || isBoardFull(board);
    }

    // Verifica se o tabuleiro está completamente preenchido
    private static boolean isBoardFull(Player[][] board) {
        for (Player[] row : board) {
            for (Player cell : row) {
                if (cell == Player.NONE) {
                    return false;
                }
            }
        }
        return false;
    }


    // Função que verifica o vencedor
    private static Player checkWinner(Player[][] board) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != Player.NONE && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0];
            }
            if (board[0][i] != Player.NONE && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return board[0][i];
            }
        }
        if (board[0][0] != Player.NONE && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0];
        }
        if (board[0][2] != Player.NONE && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2];
        }
        return Player.NONE; // Sem vencedor
    }
}