package com.plane;
//时间线程
public class TimeThread extends Thread{
		public static int time=10;
		public static int time2=10;
		GamePanel gamePanel;
	//线程在次运行
		public TimeThread(GamePanel gamePanel) {
			this.gamePanel=gamePanel;
		}
		public void run()
		{
			while(true)
			{
			try {
					Thread.sleep(1000);
					//关数加1
					time--;
					if(gamePanel.isBoosDie==true)
					{
						time2--;
					}
				System.out.println("时间1为"+time);
				System.out.println("时间2为"+time2);
				} 
			catch (InterruptedException e) {}
			}
		}
}
