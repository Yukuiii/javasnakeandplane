package com.plane;

import java.util.ArrayList;

/*
 * 控制过关画面线程
 * 作者：谭艺华
 * bug:暂时没发现
 */
public class PassFrameThread extends Thread 
{

	public static boolean isOne=true;//用于限制程序只执行一次
	GamePanel gamePanel;
	public PassFrameThread(GamePanel gamePanel) 
	{
		this.gamePanel=gamePanel;
	}
	public void run()
	{
		while(true)
		{
			//Boos死了且为第2关
			try {
				sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			if(isOne&&gamePanel.isBoosDie==true&&PlaneThread.passCount==2)
			{
				/*
				 *将敌机移开 
				 */
				//取出敌人飞机集合
				ArrayList<EnemyPlane> enemyPlanes=gamePanel.enemyPlanes;
				for(int i=0;i<enemyPlanes.size();i++)
				{
					EnemyPlane enemyPlane=enemyPlanes.get(i);
					enemyPlane.setY(enemyPlane.getY()-2000);
					//System.out.println(enemyPlane.getY());
				}
				//将我的灰机血加满----------------------------------
				gamePanel.myPlaneLefe=200;
				if(isOne==true)
				{
					try 
					{
						Thread.sleep(5000);
					} catch (InterruptedException e1) {}
				}
				if(isOne==true)
				{
					//gamePanel.setisDraw(false);
					gamePanel.backPath= "/statics/passNext.jpg";
					//停留5秒
					try 
					{
						Thread.sleep(5000);
					} catch (InterruptedException e) {}
					isOne=false;
					//gamePanel.setisDraw(true);
					gamePanel.backPath=("/statics/background_2.gif");
					//将时间设置为30
					TimeThread.time=20;
					//将我的灰机血加满----------------------------------
				
				}
			}
		}
	}

}
