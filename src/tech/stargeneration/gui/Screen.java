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
    private int maxAttempts;

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

        word = hangaroo.getWord();
        formattedWord = hangaroo.formatWord(word.toUpperCase());

        maxAttempts = hangaroo.getMaxTries();

        formattedWordLabel.setText(formattedWord);
        formattedWordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formattedWordLabel.setBounds(0, 120, 500, 30);
        formattedWordLabel.setFont(new Font("Jetbrains Mono", Font.BOLD, 40));

        maxTries.setText("Attempts: %s".formatted(String.valueOf(maxAttempts)));
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

        submitGuess.addActionListener(e -> {
            String userGuess = inputGuess.getText().toLowerCase();

            if (maxAttempts != 0) {
                checkCharacterGuess(userGuess);
            } else {
                checkWordGuess(userGuess);
            }
        });
    }

    private void addComponents() {

        add(formattedWordLabel);
        add(maxTries);
        add(wordToGuess);
        add(inputGuess);
        add(submitGuess);
    }

    private void checkCharacterGuess(String userGuess) {
        String charGuess = userGuess.substring(0,1);

        if (maxAttempts == 1) {
            showMessage("You already tried to guess the word in 3 attempts. Guess the word now");
            wordToGuess.setText("Guess the word now");
            maxTries.setText("Attempts: 0");
            formattedWord = hangaroo.deconstructFormattedWord(
                    word,
                    formattedWord,
                    charGuess
            );

            formattedWordLabel.setText(formattedWord);
            maxAttempts -= 1;
        } else {
            if (word.contains(charGuess)) {
                if (inputGuess.getText().length() > 1) {
                    showMessage("You can only guess letters in the first 3 attempts!");
                }

                formattedWord = hangaroo.deconstructFormattedWord(
                        word,
                        formattedWord,
                        charGuess
                );

                formattedWordLabel.setText(formattedWord);

                maxAttempts -= 1;
                maxTries.setText("Attempts: %s".formatted(String.valueOf(maxAttempts)));
            } else {
                showMessage("Letter is not in the word.");
            }
        }
    }

    private void checkWordGuess(String userGuess) {
        if (inputGuess
                .getText()
                .toLowerCase()
                .equals(word)) {
            showMessage("Congrats!");
        } else {
            showMessage("Bummer! Try again!");
        }

        wordToGuess.setText("The word was");
        formattedWordLabel.setText(word.toUpperCase());
    }

    private void showMessage(String messageToDisplay) {

        JOptionPane.showMessageDialog(
                null,
                messageToDisplay,
                "Message",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
