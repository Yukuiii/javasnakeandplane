package com.plane;

public class MyBullet {
	int x;//�ӵ�x����
	int y;
	int width;
	int height;
	String path= "/statics/mybullet_1.gif";//�ӵ�·��
	boolean Effect;//�ӵ��Ƿ���Ч
	MyBullet(int x,int y)
	{
		setWidth(40);
		setHeight(40);
		setEffect(true);
		setX(x);
		setY(y);
	}
	public void move() {//�ƶ�����
		//�ӵ�������Χ
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
