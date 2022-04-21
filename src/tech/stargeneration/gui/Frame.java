package tech.stargeneration.gui;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    public Frame() {

        Screen screen = new Screen();

        setTitle("Hangaroo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        add(screen);

        pack();
        setVisible(true);
    }
}
