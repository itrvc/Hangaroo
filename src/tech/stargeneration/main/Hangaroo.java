package tech.stargeneration.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangaroo {
    private File file;
    private final ArrayList<String> words;
    private final Random random;

    public Hangaroo() {
        words = new ArrayList<>();
        random = new Random();

        try {
            file = new File("C:\\Users\\itrvc\\IdeaProjects\\Hangaroo\\res\\words.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                words.add(word);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.printf("File %s does not exist.", file.getName());
        }
    }

    public void startGame() {
        Scanner input = new Scanner(System.in);
        String randomWord = words.get(6);
        String formattedWord = formatWord(randomWord);
        String userGuess;

        int maxTries = 3;
        int tryCounter = 0;

        System.out.println("Word to guess: "
                + formattedWord
                .replace("", " ")
                .toUpperCase());

        while (tryCounter != maxTries) {
            System.out.print("\nEnter a letter to complete the word:");
            userGuess = input.nextLine().toLowerCase();

            if (randomWord.contains(userGuess)) {
                formattedWord = deconstructFormattedWord(
                        randomWord,
                        formattedWord,
                        userGuess
                );

                System.out.println("Word to guess: "
                        + formattedWord
                        .replace("", " ")
                        .toUpperCase());

                tryCounter += 1;
            } else {
                System.out.printf("The letter %s is not in the word. Try again", userGuess);
                tryCounter = (tryCounter <= 0) ? 0 : tryCounter - 1;
            }

        }

        System.out.println("\nYou have already tried to guess the word in 3 attempts.");
        System.out.print("Guess the word now: ");
        userGuess = input.nextLine();

        String message = (randomWord.equals(userGuess))
                ? "\nCongratulations! You guessed the word."
                : "\nBetter luck next time! The word was %s".formatted(randomWord);

        System.out.println(message);
    }

    private String formatWord(String word) {
        int wordLength = word.length();
        int maxCoveredLetters = (wordLength <= 5) ? 3 : 5;
        int counter = 0;

        int[] randomLetters = new int[maxCoveredLetters];

        while (counter != maxCoveredLetters) {
            int randomLetter = random.nextInt(wordLength);

            for (int i = 0; i < randomLetters.length; i++) {
                if (randomLetter == randomLetters[i]) {
                    counter = (counter <= 0) ? 0 : counter - 1;
                } else {
                    randomLetters[i] = randomLetter;

                    String charToReplace = word.substring(randomLetter, randomLetter + 1);
                    word = word.replaceFirst(charToReplace, "_");

                    counter += 1;
                    i = randomLetters.length;
                }
            }
        }

        return word.replace("_", "?");
    }

    private String deconstructFormattedWord(
            String word,
            String formattedWord,
            String charUserGuess) {

        int charIndex = word.indexOf(charUserGuess);
        formattedWord =
                formattedWord.substring(0, charIndex)
                        + charUserGuess
                        + formattedWord.substring(charIndex + 1);

        return formattedWord;
    }
}
