package com.plane;

import java.util.ArrayList;

/*
 * ���ƹ��ػ����߳�
 * ���ߣ�̷�ջ�
 * bug:��ʱû����
 */
public class PassFrameThread extends Thread 
{

	public static boolean isOne=true;//�������Ƴ���ִֻ��һ��
	GamePanel gamePanel;
	public PassFrameThread(GamePanel gamePanel) 
	{
		this.gamePanel=gamePanel;
	}
	public void run()
	{
		while(true)
		{
			//Boos������Ϊ��2��
			try {
				sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			if(isOne&&gamePanel.isBoosDie==true&&PlaneThread.passCount==2)
			{
				/*
				 *���л��ƿ� 
				 */
				//ȡ�����˷ɻ�����
				ArrayList<EnemyPlane> enemyPlanes=gamePanel.enemyPlanes;
				for(int i=0;i<enemyPlanes.size();i++)
				{
					EnemyPlane enemyPlane=enemyPlanes.get(i);
					enemyPlane.setY(enemyPlane.getY()-2000);
					//System.out.println(enemyPlane.getY());
				}
				//���ҵĻһ�Ѫ����----------------------------------
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
					//ͣ��5��
					try 
					{
						Thread.sleep(5000);
					} catch (InterruptedException e) {}
					isOne=false;
					//gamePanel.setisDraw(true);
					gamePanel.backPath=("/statics/background_2.gif");
					//��ʱ������Ϊ30
					TimeThread.time=20;
					//���ҵĻһ�Ѫ����----------------------------------
				
				}
			}
		}
	}

}
