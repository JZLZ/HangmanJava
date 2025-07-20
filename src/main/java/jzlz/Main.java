package jzlz;

import jzlz.exception.GameIsFinishedException;
import jzlz.exception.LetterAlreadyInputtedException;
import jzlz.model.HangmanChar;
import jzlz.model.HangmanGame;

import java.util.Scanner;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String... args) {
        var characters = Stream.of(args)
                .map(a -> a.toLowerCase().charAt(0))
                .map(HangmanChar::new).toList();
        System.out.println(characters);
        var hangmanGame = new HangmanGame(characters);
        System.out.println("Bem vindo ao jogo da forca, tente adivinhar a palavra. Boa sorte!");
        System.out.println(hangmanGame);
        var option = -1;
        while (true){
            System.out.println("Selecione uma opção");
            System.out.println("1 - Incormar uma letra");
            System.out.println("2 - Verificar Status do jogo");
            System.out.println("3 - Sair");
            option = scanner.nextInt();
            switch (option){
                case 1 -> inputCharacter(hangmanGame);
                case 2 -> showGameStatus(hangmanGame);
                case 3 -> System.exit(0);
                default -> System.out.println("Opção invalida!");
            }
        }
    }

    private static void showGameStatus(HangmanGame hangmanGame) {
        System.out.println(hangmanGame.getHangmanGameStatus());
    }

    private static void inputCharacter(HangmanGame hangmanGame) {
        System.out.println("Informe uma letra");
        var character = scanner.next().charAt(0);
        try {
            hangmanGame.inputCharacter(character);
        }catch (LetterAlreadyInputtedException ex){
            System.out.println(ex.getMessage());
        }catch (GameIsFinishedException ex){
            System.out.println(ex.getMessage());
            System.exit(0);
        }
        System.out.println(hangmanGame);
    }
}