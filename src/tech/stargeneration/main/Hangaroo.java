package tech.stargeneration.main;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangaroo {

    private final ArrayList<String> words;
    private final Random random;
    private String word;
    private int maxTries;

    public Hangaroo() {
        words = new ArrayList<>();
        random = new Random();

//        try {
//            file = new File(".\\res\\words.txt");
//
//            Scanner scanner = new Scanner(file);
//
//            while (scanner.hasNextLine()) {
//                String word = scanner.nextLine();
//                words.add(word);
//            }
//
//            scanner.close();
//        } catch (FileNotFoundException e) {
//            System.out.printf("File %s does not exist.", file.getName());
//        }

        words.add("bling");
        words.add("jumpy");
        words.add("abductions");
        words.add("uncopyrightable");
        words.add("abortively");
        words.add("freshly");
        words.add("jackbox");
        words.add("dumbing");
        words.add("blacksmith");
        words.add("gunpowdery");
        words.add("excellence");
        words.add("typing");
        words.add("sharing");
        words.add("pancake");
        words.add("express");
        words.add("music");
        words.add("idiom");
        words.add("khentray");
        words.add("command");
        words.add("aesthetic");
        words.add("welcome");
        words.add("voices");
        words.add("havoc");
        words.add("delicacy");
        words.add("extravagant");
        words.add("glamour");
        words.add("vigorous");
        words.add("hammock");
        words.add("replenish");

        int randomWord = random.nextInt(words.size());
        word = words.get(random.nextInt(randomWord));
        maxTries = 3;
    }

    // Use start game if you want to play in the CLI.
    public void startGame() {
        Scanner input = new Scanner(System.in);
        String randomWord = words.get(random.nextInt(words.size()));
        String formattedWord = formatWord(randomWord);
        String userGuess;

        int tryCounter = 0;

        System.out.println("Word to guess: "
                + formattedWord
                .replace("", " ")
                .toUpperCase());

        while (tryCounter != maxTries) {
            System.out.print("\nEnter a letter to complete the word: ");
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
                System.out.printf("The letter %s is not in the word. Try again\n", userGuess);
            }

        }

        System.out.printf("\nYou have already tried to guess the word in %d attempts.", maxTries);
        System.out.print("\nGuess the word now: ");
        userGuess = input.nextLine();

        String message = (randomWord.equals(userGuess))
                ? "\nCongratulations! You guessed the word."
                : "\nBetter luck next time! The word was %s".formatted(randomWord);

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

    public String getWord() {

        return this.word;
    }

    public int getMaxTries() {
        return this.maxTries;
    }
}
