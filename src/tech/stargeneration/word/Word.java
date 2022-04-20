package tech.stargeneration.word;

import java.util.Random;

public class Word {

    private final Random random;
    private final String word;

    public Word(String word) {

        this.random = new Random();
        this.word = word;
    }

    public String getFormattedWord() {

        int wordLength = this.word.length();
        int wordMaxUnknownLetters = (wordLength <= 5) ? 3 : 5;
        int wordCounter = 0;
        int[] wordRandomLettersIndex = new int[wordMaxUnknownLetters];

        String[] wordArray = this.word.split("");
        String wordTemporary = this.word;
        String wordFormatted = "";

        while (wordCounter != wordMaxUnknownLetters) {
            int wordRandomLetter = random.nextInt(wordLength);

            for (int i = 0; i < wordRandomLettersIndex.length; i++) {
                if (wordRandomLetter != wordRandomLettersIndex[i]) {
                    wordRandomLettersIndex[i] = wordRandomLetter;
                    wordTemporary = wordTemporary.replaceFirst(wordArray[wordRandomLetter], "_");
                    wordFormatted = wordTemporary;

                    wordCounter += 1;

                    i = wordRandomLettersIndex.length;
                } else {
                    wordCounter = (wordCounter <= 0) ? 0 : wordCounter - 1;
                }
            }
        }

        return wordFormatted.replace("", " ").toUpperCase();
    }
}
