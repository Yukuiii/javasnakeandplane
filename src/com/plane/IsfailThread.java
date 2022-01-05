package com.plane;


import javax.swing.*;

public class IsfailThread extends Thread{
    private GamePanel gamePanel;
    public static boolean isOne = true;
    public IsfailThread(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void run() {
        while (true) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (gamePanel.myPlaneLefe<=0&&isOne){
                new FailFrame(gamePanel);
               PlaneThread.Isfail=false;
                isOne=false;
            }
        }
    }
}
