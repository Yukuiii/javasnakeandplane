package com.plane;

import javax.swing.JFrame;

public class PlaneFrame extends JFrame{
	public static int WIDTH=500;
	public static int HEIGHT=600;
	public PlaneFrame(GamePanel gamePanel) {
		setSize(WIDTH, HEIGHT);
		setTitle("空战");
		setLocationRelativeTo(null);//居中
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(gamePanel);//将gamePanel放入到PlaneFrame当中
		setVisible(true);
		setResizable(false);
	}
}
