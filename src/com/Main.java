package com;

import com.plane.IsfailThread;
import com.plane.Plane;
import com.plane.PlaneThread;
import com.snake.StartGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        JFrame j1 = new JFrame("游戏主窗口");
        j1.setSize(400, 200);
        j1.setResizable(false);
        j1.setLocationRelativeTo(null);
        j1.setLayout(null);
        j1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton btn2 = new JButton("飞机大战");
        JButton btn1 = new JButton("贪吃蛇");
        btn1.setSize(120, 60);
        btn1.setLocation(40, 50);
        btn1.setVisible(true);
        btn2.setSize(120, 60);
        btn2.setLocation(240, 50);
        btn2.setVisible(true);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("贪吃蛇")) {
                    StartGame startGame =new StartGame();
                    startGame.StartGame();
                    j1.setVisible(false);
                }
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("飞机大战")) {
                    Plane plane = new Plane();
                    plane.Plane();
                    j1.setVisible(false);

                }
            }
        });
        j1.add(btn2);
        j1.add(btn1);
        j1.setVisible(true);
    }
}
