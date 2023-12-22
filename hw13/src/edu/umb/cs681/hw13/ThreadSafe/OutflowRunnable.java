package edu.umb.cs681.hw13.ThreadSafe;

import java.util.concurrent.atomic.AtomicBoolean;


public class OutflowRunnable implements Runnable{


	
	AtomicBoolean done = new AtomicBoolean(false);
	private ThreadSafeWaterTankMonitor tswtm;

	public OutflowRunnable(ThreadSafeWaterTankMonitor waterTank) {
		// TODO Auto-generated constructor stub
		this.tswtm = waterTank;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0; i<10;i++) {
			if (done.get()) {
				System.out.println(Thread.currentThread().getName()+"Thread terminated");
				break;
			}
			this.tswtm.OutflowLiters(1000.00);
			try {
				Thread.sleep(2000 );
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Interrupt successfull");
			}
		}
	}
	public void setDone() {
		this.done.set(true);;
	}

}
