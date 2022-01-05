package com.snake;

import javax.swing.*;

public class StartGame {

    public void StartGame() {
        JFrame frame = new JFrame("Ã∞≥‘…ﬂ–°”Œœ∑");
        frame.setSize(900,720);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GamePanel());
        frame.setVisible(true);
    }

}
