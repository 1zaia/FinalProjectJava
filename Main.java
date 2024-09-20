import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem-vindo ao Jogo da Velha!");
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Jogar 1v1 (dois jogadores)");
        System.out.println("2 - Jogar contra o computador (fácil)");
        System.out.println("3 - Jogar contra o computador (difícil)");

        int escolha = scanner.nextInt();
        Matriz.matriz(escolha);
    }
}
