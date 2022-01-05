package com.plane;
/*
 * 用与计算BOOS与我灰机碰撞后 无效时间线程
 * 作者：谭艺华
 * bug:暂时没发现
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
