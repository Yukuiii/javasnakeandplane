package com.plane;

public class Myplane {
	private int x;
	private int y;
	private int width;
	private int heigth;
	private String path;
	boolean isFire;//�һ��Ƿ񿪻�
	//�һ��Ĺ��캯��
	public Myplane(){
		x=PlaneFrame.WIDTH/2-40;//x����
		y=PlaneFrame.HEIGHT-80;//y����
		width=45;
		heigth=45;
		colsePlane();
		isFire=false;
	}
	//ѡ��ս����ͼƬ·��
	
	public void colsePlane() {
		// TODO Auto-generated method stub
		path= "/statics/myplane_04.png";
	}

	
	public void move() {
		// TODO Auto-generated method stub
		
	}

	
	public boolean toFire() {
		// TODO Auto-generated method stub
		return false;
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

	public int getHeigth() {
		return heigth;
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isFire() {
		return isFire;
	}

	public void setFire(boolean isFire) {
		this.isFire = isFire;
	}
	
}
