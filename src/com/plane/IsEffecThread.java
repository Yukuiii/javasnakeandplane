package com.plane;
/*
 * �������BOOS���һһ���ײ�� ��Чʱ���߳�
 * ���ߣ�̷�ջ�
 * bug:��ʱû����
 */
public class IsEffecThread extends Thread 
{
	public void run()
	{
		while (true) 
		{
			//System.out.println(PlaneThread.isEffec);
			try {
				sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			if(PlaneThread.isEffec==false)
			{
				try 
				{
					Thread.sleep(500);
					PlaneThread.isEffec=true;
				} catch (InterruptedException e) {}
			}
		}
	}
}
