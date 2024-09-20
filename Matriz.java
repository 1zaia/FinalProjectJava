import java.util.Scanner;

public class Matriz {
    static Game gameState = new Game();
    static int[][] game = new int[3][3];
    static Scanner scan = new Scanner(System.in);

    // Função para iniciar o jogo com base na escolha do jogador
    static void matriz(int escolha) {
        // Inicializa a matriz do jogo (0 significa posição vazia)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                game[i][j] = 0;
            }
        }

        // Loop principal do jogo
        while (true) {
            printBoard();

            if (gameState.countPlayer == 1 || escolha == 1) {
                // Turno do jogador humano
                int row, column;

                do {
                    System.out.print("Jogador " + gameState.countPlayer + ", escolha sua posição (linha e coluna 0-2): ");
                    row = scan.nextInt();
                    column = scan.nextInt();

                    if (row < 0 || row > 2 || column < 0 || column > 2) {
                        System.out.println("Posição inválida. Tente novamente.");
                    } else if (game[row][column] != 0) {
                        System.out.println("Essa posição já está ocupada. Tente outra.");
                    } else {
                        break;
                    }
                } while (true);

                // Marca a jogada do jogador
                game[row][column] = gameState.countPlayer;
                gameState.contTable++;
            } else {
                // Turno do computador
                if (escolha == 2) {
                    // Nível fácil
                    ComputerPlayer.makeMoveEasy(game);
                } else if (escolha == 3) {
                    // Nível difícil
                    ComputerPlayer.makeMoveHard(game);
                }
                gameState.contTable++;
            }

            // Verifica se alguém venceu
            if (gameState.checkWinner(game)) {
                printBoard();
                System.out.println("Jogador " + gameState.countPlayer + " venceu!");
                break;
            }

            // Verifica se deu empate
            if (gameState.isDraw()) {
                printBoard();
                System.out.println("Empate!");
                break;
            }

            // Alterna o jogador (1 para jogador humano, 2 para computador ou outro jogador)
            gameState.countPlayer = (gameState.countPlayer == 1) ? 2 : 1;
        }
    }

    // Função para exibir o tabuleiro do jogo
    static void printBoard() {
        System.out.println("Tabuleiro atual:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game[i][j] == 0) {
                    System.out.print("- ");
                } else {
                    System.out.print(game[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
