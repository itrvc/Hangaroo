package tech.stargeneration.gui;

import tech.stargeneration.main.Hangaroo;

import javax.swing.*;
import java.awt.*;

public class Screen extends JPanel {

    private JLabel formattedWordLabel;
    private JLabel maxTries;
    private JLabel wordToGuess;
    private JTextField inputGuess;
    private JButton submitGuess;
    private Hangaroo hangaroo;
    private String word;
    private String formattedWord;

    public Screen() {

        int SIZE = 500;
        Dimension dimension = new Dimension(SIZE, SIZE);

        initComponents();

        setLayout(null);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);

        addComponents();
    }

    private void initComponents() {

        formattedWordLabel = new JLabel();
        maxTries = new JLabel();
        wordToGuess = new JLabel();
        inputGuess = new JTextField();
        submitGuess = new JButton();
        hangaroo = new Hangaroo();

        word = hangaroo.getWord().toUpperCase();
        formattedWord = hangaroo.formatWord(word);

        formattedWordLabel.setText(formattedWord);
        formattedWordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formattedWordLabel.setBounds(0, 120, 500, 30);
        formattedWordLabel.setFont(new Font("Jetbrains Mono", Font.BOLD, 40));

        maxTries.setText("Attempts: %s".formatted(String.valueOf(hangaroo.getMaxTries())));
        maxTries.setBounds(0, 0, 300, 30);
        maxTries.setFont(new Font("Jetbrains Mono", Font.PLAIN, 15));

        wordToGuess.setText("Word to guess");
        wordToGuess.setHorizontalAlignment(SwingConstants.CENTER);
        wordToGuess.setBounds(0, 70, 500, 30);
        wordToGuess.setFont(new Font("Jetbrains Mono", Font.PLAIN, 15));

        inputGuess.setText("Enter letter");
        inputGuess.setHorizontalAlignment(SwingConstants.CENTER);
        inputGuess.setBounds(155, 200, 200, 30);
        inputGuess.setFont(new Font("Jetbrains Mono", Font.PLAIN, 20));

        submitGuess.setText("Guess");
        submitGuess.setHorizontalAlignment(SwingConstants.CENTER);
        submitGuess.setBounds(125, 300, 250, 30);
        submitGuess.setFont(new Font("Jetbrains Mono", Font.BOLD, 20));
    }

    private void addComponents() {

        add(formattedWordLabel);
        add(maxTries);
        add(wordToGuess);
        add(inputGuess);
        add(submitGuess);
    }
}
