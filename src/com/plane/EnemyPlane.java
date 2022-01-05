package com.plane;

import java.util.Random;


//敌机类
public class EnemyPlane {
	private int x;
	private int y;
	private int width;
	private int heigth;
	private String path;
	private boolean isFire;
	public EnemyPlane() {
		Random random=new Random();
		setX(random.nextInt(PlaneFrame.WIDTH));//敌机随机的坐标值
		setY(random.nextInt(PlaneFrame.HEIGHT));
		width=45;
		heigth=45;
		colsePlane();		

	}
	/*
	 * 敌机开火
	 */
	public boolean toFire()
	{
		Random random=new Random();
		return random.nextInt(700)==1?true:false;//50分之1开火概率
	}
	/*
	 * 敌人飞机移动方法
	 */
	public void move()
	{
		Random random=new Random();
		//如果灰机超出范围 X坐标重新设置
		if(getY()>PlaneFrame.HEIGHT-10)
		{
			setX(random.nextInt(PlaneFrame.WIDTH-20));
		}
		setY(getY()+2);
		setX(random.nextInt(2)==1?getX()+1:getX()-1);
	}
	public void colsePlane() {
		// TODO Auto-generated method stub
		Random random=new Random();//取随机数
		int closePlaneNum=random.nextInt(9);//随机选择敌机
		if(closePlaneNum==0)
			setPath("/statics/enemy_1.gif");
		else if(closePlaneNum==1)
			setPath("/statics/enemy_2.png");
		else if(closePlaneNum==2)
			setPath("/statics/enemy_3.png");
		else if(closePlaneNum==3)
			setPath("/statics/enemy_4.png");
		else if(closePlaneNum==4)
			setPath("/statics/enemy_5.png");
		else if(closePlaneNum==5)
			setPath("/statics/enemy_6.png");
		else if(closePlaneNum==6)
			setPath("/statics/enemy_7.png");
		else if(closePlaneNum==7)
			setPath("/statics/enemy_8.png");
		else if(closePlaneNum==8)
			setPath("/statics/enemy_9.png");
	
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
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
	public void setY(int y) {
		if(y>PlaneFrame.HEIGHT)
			y=-y/2;
		this.y = y;
	}
	public void setX(int x) {
		if(x<0)
			x=0;
		if(x>PlaneFrame.WIDTH-50)
			x=PlaneFrame.WIDTH-50;
		this.x = x;
	}
}
