package com.plane;

/*
 * 第一关BOOS
 * 作者：谭艺华
 * bug:暂时没发现
 */
public class Boos_1 
{
	int x;//飞机在面板上的x坐标
	int y;//飞机在面板上的y坐标
	int width;//飞机在面板上的宽度
	int height;//飞机在面板上的高度
	String path;//飞机的图片路径
	int left=300;//敌机生命默认300生命
	/*
	 * 构造函数
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
	 * 敌人飞机移动方法
	 */
	boolean isLeft=true;//判断BOOS往哪走
	boolean istop=true;//判断BOOS往哪走
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
	 * BOOS开火
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


