import java.util.Scanner;

public class Matriz {
    static Winner cond = new Winner();
    static int[][] game = new int[3][3]; 
    static Scanner scan = new Scanner(System.in);

    static void matriz() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                game[i][j] = 0;
            }
        }

        while(cond.condition()) {
            int row, column;

            do {
                System.out.print("Posição de onde você quer jogar (linha e coluna 0-2): ");
                row = scan.nextInt();
                column = scan.nextInt();

                if (row < 0 || row > 2 || column < 0 || column > 2) {
                    System.out.println("Posição inválida. Tente novamente.");
                } else if (game[row][column] != 0) {
                    System.out.println("Essa posição já está ocupada. Tente outra.");
                } else {
                    break; 
                }
                cond.contTable++;
            } while (true);

            game[row][column] = 1;

        }
    }
}

