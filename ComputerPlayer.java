import java.util.Random;

public class ComputerPlayer {

    // Nível fácil: Jogada aleatória
    public static void makeMoveEasy(int[][] game) {
        Random rand = new Random();
        int row, column;

        // Tenta encontrar uma posição aleatória livre
        do {
            row = rand.nextInt(3); // Gera um número entre 0 e 2
            column = rand.nextInt(3); // Gera um número entre 0 e 2
        } while (game[row][column] != 0); // Repete até encontrar uma posição livre

        // Marca a jogada do computador
        game[row][column] = 2; // Computador será o jogador 2
        System.out.println("O computador (fácil) jogou na posição (" + row + ", " + column + ")");
    }

    // Nível difícil: Estratégia básica para ganhar ou bloquear
    public static void makeMoveHard(int[][] game) {
        // Estratégia: tentar ganhar ou bloquear o jogador
        if (!tryToWinOrBlock(game, 2)) { 
            if (!tryToWinOrBlock(game, 1)) { 
                makeMoveEasy(game); 
            }
        }
        System.out.println("O computador (difícil) fez sua jogada.");
    }

    // Função auxiliar para tentar vencer ou bloquear
    
        // Função auxiliar para tentar ganhar ou bloquear
        private static boolean tryToWinOrBlock(int[][] game, int player) {
            // Verifica as linhas e colunas
            for (int i = 0; i < 3; i++) {
                // Verifica as linhas
                if (game[i][0] == player && game[i][1] == player && game[i][2] == 0) {
                    game[i][2] = 2;
                    return true;
                }
                if (game[i][0] == player && game[i][2] == player && game[i][1] == 0) {
                    game[i][1] = 2;
                    return true;
                }
                if (game[i][1] == player && game[i][2] == player && game[i][0] == 0) {
                    game[i][0] = 2;
                    return true;
                }
    
                // Verifica as colunas
                if (game[0][i] == player && game[1][i] == player && game[2][i] == 0) {
                    game[2][i] = 2;
                    return true;
                }
                if (game[0][i] == player && game[2][i] == player && game[1][i] == 0) {
                    game[1][i] = 2;
                    return true;
                }
                if (game[1][i] == player && game[2][i] == player && game[0][i] == 0) {
                    game[0][i] = 2;
                    return true;
                }
            }
    
            // Verifica a diagonal principal (canto superior esquerdo para o canto inferior direito)
            if (game[0][0] == player && game[1][1] == player && game[2][2] == 0) {
                game[2][2] = 2;
                return true;
            }
            if (game[0][0] == player && game[2][2] == player && game[1][1] == 0) {
                game[1][1] = 2;
                return true;
            }
            if (game[1][1] == player && game[2][2] == player && game[0][0] == 0) {
                game[0][0] = 2;
                return true;
            }
    
            // Verifica a diagonal inversa (canto superior direito para o canto inferior esquerdo)
            if (game[0][2] == player && game[1][1] == player && game[2][0] == 0) {
                game[2][0] = 2;
                return true;
            }
            if (game[0][2] == player && game[2][0] == player && game[1][1] == 0) {
                game[1][1] = 2;
                return true;
            }
            if (game[1][1] == player && game[2][0] == player && game[0][2] == 0) {
                game[0][2] = 2;
                return true;
            }
    
            return false; // Se não encontrar nenhuma jogada, retorna falso
        }
    
}
