package com.plane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FailFrame {

    public FailFrame(GamePanel gamePanel) {
        JFrame j1 = new JFrame();
        j1.setSize(400, 200);
        j1.setResizable(false);
        j1.setLocationRelativeTo(null);
        j1.setLayout(null);
        JButton btn2 = new JButton("结束游戏");
        JButton btn1 = new JButton("复活大法！");
        btn1.setSize(120, 60);
        btn1.setLocation(40, 50);
        btn1.setVisible(true);
        btn2.setSize(120, 60);
        btn2.setLocation(200, 50);
        btn2.setVisible(true);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("复活大法！")) {
//                    System.out.println("123");
                    PlaneThread.Isfail = true;
                    gamePanel.myPlaneLefe = 200;
                    PlaneThread planeThread = new PlaneThread(gamePanel);
                    planeThread.start();
                    j1.setVisible(false);
                    IsfailThread.isOne=true;

                }
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("结束游戏")) {
                    System.exit(0);

                }
            }
        });
        j1.add(btn2);
        j1.add(btn1);
        j1.setVisible(true);
    }
}

