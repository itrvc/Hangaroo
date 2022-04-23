package tech.stargeneration.main;

import java.util.ArrayList;
import java.util.Random;

public class Hangaroo {

    private final Random random;
    private final String word;
    private final int maxTries;

    public Hangaroo() {
        ArrayList<String> words = new ArrayList<>();
        random = new Random();

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
