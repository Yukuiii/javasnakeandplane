package com.plane;

import javax.swing.JFrame;

public class PlaneFrame extends JFrame{
	public static int WIDTH=500;
	public static int HEIGHT=600;
	public PlaneFrame(GamePanel gamePanel) {
		setSize(WIDTH, HEIGHT);
		setTitle("��ս");
		setLocationRelativeTo(null);//����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(gamePanel);//��gamePanel���뵽PlaneFrame����
		setVisible(true);
		setResizable(false);
	}
}
