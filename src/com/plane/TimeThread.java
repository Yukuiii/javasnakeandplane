package com.plane;
//ʱ���߳�
public class TimeThread extends Thread{
		public static int time=10;
		public static int time2=10;
		GamePanel gamePanel;
	//�߳��ڴ�����
		public TimeThread(GamePanel gamePanel) {
			this.gamePanel=gamePanel;
		}
		public void run()
		{
			while(true)
			{
			try {
					Thread.sleep(1000);
					//������1
					time--;
					if(gamePanel.isBoosDie==true)
					{
						time2--;
					}
				System.out.println("ʱ��1Ϊ"+time);
				System.out.println("ʱ��2Ϊ"+time2);
				} 
			catch (InterruptedException e) {}
			}
		}
}
