package com.plane;

/*
 * ��һ��BOOS
 * ���ߣ�̷�ջ�
 * bug:��ʱû����
 */
public class Boos_1 
{
	int x;//�ɻ�������ϵ�x����
	int y;//�ɻ�������ϵ�y����
	int width;//�ɻ�������ϵĿ��
	int height;//�ɻ�������ϵĸ߶�
	String path;//�ɻ���ͼƬ·��
	int left=300;//�л�����Ĭ��300����
	/*
	 * ���캯��
	 */
	public Boos_1() 
	{
		setPath("/statics/boss_1.gif");
		setWidth(150);
		setHeight(150);
		setX(0);
		setY(0);
	}
	/*
	 * ���˷ɻ��ƶ�����
	 */
	boolean isLeft=true;//�ж�BOOS������
	boolean istop=true;//�ж�BOOS������
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
	/*
	 * BOOS����
	 */
	public void toFire()
	{
		
	}
	//******************************************************
	public int getX() {
		return x;
	}
	public void setX(int x) {
		if(x<0)
			x=0;
		if(x>PlaneFrame.WIDTH-50)
			x=PlaneFrame.WIDTH-50;
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		if(y>PlaneFrame.HEIGHT)
			y=-y/2;
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


