package com.plane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
GamePanel gamePanel;
    public Frame(GamePanel gamePanel) throws HeadlessException {

        setTitle("恭喜你！");
        setSize(400,200);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        JLabel l1 =new JLabel("恭喜你通关拉！你的积分是"+gamePanel.score);
        JButton btn2 = new JButton("结束游戏");
        l1.setSize(200,60);
        l1.setLocation(100,10);
        btn2.setSize(120, 60);
        btn2.setLocation(140, 80);
        l1.setVisible(true);
        btn2.setVisible(true);
        add(l1);
        add(btn2);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("结束游戏")) {
                    System.exit(0);

                }
            }
        });
        setVisible(true);
        PlaneThread.Isfail = false;

    }
}
