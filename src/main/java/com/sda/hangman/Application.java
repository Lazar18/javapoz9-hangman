package com.sda.hangman;

import com.sda.hangman.domain.HangmanGameService;
import com.sda.hangman.domain.model.GameStatus;
import com.sda.hangman.domain.port.PhraseRepository;
import com.sda.hangman.infrastructure.memory.MemoryPhraseRepository;

import java.util.Scanner;

public class Application {

    private static Scanner scanner;
    private static PhraseRepository phraseRepository;
    private static HangmanGameService hangmanGameService;

    public static void main(String[] args) {
        hangmanGameService = new HangmanGameService();
        phraseRepository = new MemoryPhraseRepository();
        scanner = new Scanner(System.in);
        boolean menuFlag = true;
        do {
            System.out.println("1. Start");
            System.out.println("2. Wyniki");
            System.out.println("Inne. Koniec");
            int decision = scanner.nextInt();
            scanner.nextLine();

            switch (decision) {
                case 1:
                    startGame();
                    break;
                case 2:
                    System.out.println("Logika do wyników");
                    break;
                default:
                    menuFlag = false;
            }
        } while (menuFlag);
    }

    public static void startGame() {
        System.out.println("Podaj imię");
        String name = scanner.nextLine();
        System.out.println("Kliknij enter aby rozpocząć grę");
        String phrase = phraseRepository.getPhrase();
        GameStatus gameStatus = hangmanGameService.createGameStatus(name, phrase);

        do {
            System.out.println(gameStatus.getCurrentStatePhraseWithLeftAttempts());
            System.out.println("Podaj kolejną literę:");
            char letter = scanner.nextLine().charAt(0);
            hangmanGameService.processNextLetter(letter, gameStatus);
        }
        while (!gameStatus.isGameFinished());

        switch (gameStatus.getFinishedGameStatus()) {
            case WON:
                System.out.println("Gratulacje " + name + ". Wygrałeś w " + gameStatus.getTotalAttempts() + " krokach");
                break;
            case LOSE:
                System.out.println(name + "... Przegrałeś!");
                break;
            case RUNNING:
                System.out.println("Gra się toczy...");
        }
    }
}
