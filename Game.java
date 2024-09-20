public class Game {

    public int countPlayer = 1;
    public int contTable = 0;

    // Verifica se um jogador venceu
    public boolean checkWinner(int[][] game) {
        // Verificar linhas e colunas
        for (int i = 0; i < 3; i++) {
            if (game[i][0] == game[i][1] && game[i][1] == game[i][2] && game[i][0] != 0)
                return true;
            if (game[0][i] == game[1][i] && game[1][i] == game[2][i] && game[0][i] != 0)
                return true;
        }
        
        // Verificar diagonais
        if (game[0][0] == game[1][1] && game[1][1] == game[2][2] && game[0][0] != 0)
            return true;
        if (game[0][2] == game[1][1] && game[1][1] == game[2][0] && game[0][2] != 0)
            return true;

        return false;
    }

    // Verifica se o jogo terminou por empate
    public boolean isDraw() {
        return contTable >= 9;
    }
}

