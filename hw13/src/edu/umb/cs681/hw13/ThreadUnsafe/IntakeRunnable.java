package edu.umb.cs681.hw13.ThreadUnsafe;

import java.util.concurrent.atomic.AtomicBoolean;



public class IntakeRunnable implements Runnable {

	
//	private AtomicBoolean done = new AtomicBoolean(false);
	boolean done= false;
	private ThreadUnsafeWaterTankMonitor tuwtm;
	public boolean getDone() {
		return this.done;
	}

	public IntakeRunnable(ThreadUnsafeWaterTankMonitor waterTank) {
		// TODO Auto-generated constructor stub
		this.tuwtm=waterTank;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0; i<10;i++) {
			if (done) {
				System.out.println(Thread.currentThread().getName()+"Thread terminated");
				break;
			}
			this.tuwtm.IntakeLiters(1000.00);
			try {
				Thread.sleep( 2000 );
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Interrupt successfull");
			}
		}

	}
	
	public void setDone() {
		done=true;
	}
}
