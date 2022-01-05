package com.plane;

public class MyBullet {
	int x;//子弹x坐标
	int y;
	int width;
	int height;
	String path= "/statics/mybullet_1.gif";//子弹路径
	boolean Effect;//子弹是否有效
	MyBullet(int x,int y)
	{
		setWidth(40);
		setHeight(40);
		setEffect(true);
		setX(x);
		setY(y);
	}
	public void move() {//移动方法
		//子弹超出范围
				if(getY()<0)
					Effect=false;
				setX(x);
				setY(getY()-2);
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
	public boolean isEffect() {
		return Effect;
	}
	public void setEffect(boolean effect) {
		Effect = effect;
	}
	
}
