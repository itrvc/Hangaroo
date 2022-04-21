package tech.stargeneration.gui;

import tech.stargeneration.main.Hangaroo;

import javax.swing.*;
import java.awt.*;

public class Screen extends JPanel {

    private JLabel formattedWordLabel;
    private JLabel maxTries;
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
        inputGuess = new JTextField();
        submitGuess = new JButton();
        hangaroo = new Hangaroo();

        word = hangaroo.getWord().toUpperCase();
        formattedWord = hangaroo.formatWord(word);

        formattedWordLabel.setText(formattedWord);
        formattedWordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formattedWordLabel.setBounds(0, 150, 500, 30);
        formattedWordLabel.setFont(new Font("Jetbrains Mono", Font.BOLD, 40));
    }

    private void addComponents() {

        add(formattedWordLabel);
    }
}
