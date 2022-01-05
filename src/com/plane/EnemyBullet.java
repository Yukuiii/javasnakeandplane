package com.plane;

public class EnemyBullet {
	int x;
	int y;
	int width;
	int height;
	String path;
	boolean isEffect;
	
	public EnemyBullet(int x,int y){
		setPath("/statics/efire_1.gif");
		setX(x);
		setY(y);
		setWidth(20);
		setHeight(20);
		setEffect(true);
	}
	
	/**
	 * 敌机子弹移动的方法
	 */
	public void move(){
		setY(getY()+3);
		if(getY()>PlaneFrame.HEIGHT)
		{
			setEffect(false);
		}
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
		return isEffect;
	}
	public void setEffect(boolean isEffect) {
		this.isEffect = isEffect;
	}
	
}
