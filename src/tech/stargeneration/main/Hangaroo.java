package tech.stargeneration.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangaroo {

    private File file;
    private final Random random;
    private final ArrayList<String> words;
    private final int maxTries;


    public Hangaroo() {
        words = new ArrayList<>();
        random = new Random();

        try {
            file = new File(".\\res\\words.txt");

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                words.add(word);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.printf("File %s does not exi st.", file.getName());
        }

        maxTries = 3;
    }

    public void startGame() {
        Scanner input = new Scanner(System.in);
        String randomWord = words.get(random.nextInt(words.size()));
        String formattedWord = formatWord(randomWord);
        String userGuess;

        int tryCounter = 0;

        System.out.println("Word to guess:"
                + formattedWord
                .replace("", " ")
                .toUpperCase());

        while (tryCounter != maxTries) {
            System.out.print("\nEnter a letter to complete the word: ");
            userGuess = input.nextLine().toLowerCase();

            if (userGuess.length() > 1) {
                System.out.println("You can only guess letters in the first 3 attempts.");
            } else {
                userGuess = userGuess.substring(0, 1);
                if (randomWord.contains(userGuess)) {
                    formattedWord = deconstructFormattedWord(
                            randomWord,
                            formattedWord,
                            userGuess
                    );

                    System.out.println("Word to guess:"
                            + formattedWord
                            .replace("", " ")
                            .toUpperCase());

                    tryCounter += 1;
                } else {
                    System.out.printf(
                            "The letter %s is not in the word. Try again\n",
                            userGuess.toUpperCase());
                }
            }

        }

        System.out.printf("\nYou have already tried to guess the word in %d attempts.", maxTries);
        System.out.print("\nGuess the word now: ");
        userGuess = input.nextLine();

        String message = (randomWord.equals(userGuess))
                ? "\nCongratulations! You guessed the word."
                : "\nBetter luck next time! The word was %s".formatted(randomWord.toUpperCase());

        System.out.println(message);
    }

    public String formatWord(String word) {
        int wordLength = word.length();
        int maxCoveredLetters = (wordLength <= 5) ? 4 : 6;
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

    public String deconstructFormattedWord(
            String word,
            String formattedWord,
            String charUserGuess) {

        int charIndex = word.indexOf(charUserGuess);
        formattedWord =
                formattedWord.substring(0, charIndex)
                        + charUserGuess.toUpperCase()
                        + formattedWord.substring(charIndex + 1);

        return formattedWord;
    }
}
