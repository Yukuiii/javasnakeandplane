package com.plane;

import java.util.Random;

public class RedHp {
    int x;
    int y;
    int width;
    int height;
    String path;

    public RedHp() {
        Random random = new Random();
        setPath("/statics/red.png");
        setWidth(60);
        setHeight(60);
        setX(random.nextInt(PlaneFrame.WIDTH));
        setY(-50);
    }

    public void move() {
        setY(getY() + 1);
    }
    //    100/1的几率生成血包
    public boolean isTrue() {
        Random random = new Random();
        return random.nextInt(100) == 1 ? true : false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
