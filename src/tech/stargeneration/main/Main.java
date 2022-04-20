package tech.stargeneration.main;

import tech.stargeneration.word.Word;

public class Main {

    public static void main(String[] args) {
        Word word = new Word("Hello");
        System.out.println(word.getFormattedWord());
    }
}
