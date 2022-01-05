package com.plane;

public class Plane {

    public void Plane(){
        // TODO Auto-generated method stub
        GamePanel gamePanel = new GamePanel();
        PlaneFrame planeFrame = new PlaneFrame(gamePanel);
        PlaneThread planeThread = new PlaneThread(gamePanel);
        planeThread.start();
        planeThread.setPriority(1);
        TimeThread timeThread = new TimeThread(gamePanel);
        timeThread.start();
        //timeThread.setPriority(1);
        IsEffecThread isEffecThread = new IsEffecThread();
        isEffecThread.start();
        PassFrameThread passFrameThread = new PassFrameThread(gamePanel);
        passFrameThread.start();
        IsfailThread isfailThread = new IsfailThread(gamePanel);
        isfailThread.start();
        //isEffecThread.setPriority(1);
    }

}
