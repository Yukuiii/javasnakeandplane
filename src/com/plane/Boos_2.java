package com.plane;

public class Boos_2 {
	int x;
	int y;
	int width;
	int height;
	String path;
	int left=1000;
	
	
	public Boos_2() {
		setPath("/statics/boss_2.png");
		setY(0);
		setX(0);
		setHeight(150);
		setWidth(150);
	}
	boolean isLeft=true;
	boolean istop=true;
	public void move()
	{
		if(getY()>PlaneFrame.HEIGHT-65)
			istop=true;
		else if(getY()<0)
			istop=false;
		if(istop==true)
			setY(getY()-2);
		if(istop==false)
			setY(getY()+5);

		if(getX()<20)
			isLeft=false;
		else if(getX()>PlaneFrame.WIDTH-55)
			isLeft=true;
		if(isLeft==true)
			setX(getX()-2);
		if(isLeft==false)
			setX(getX()+2);
		//System.out.println(getX());
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


	public int getLeft() {
		return left;
	}


	public void setLeft(int left) {
		this.left = left;
	}
	
	
	
	
	
}


