package edu.umb.cs681.hw13.ThreadUnsafe;





public class OutflowRunnable implements Runnable{


	
	boolean done = false;
	private ThreadUnsafeWaterTankMonitor tuwtm;

	public OutflowRunnable(ThreadUnsafeWaterTankMonitor waterTank) {
		// TODO Auto-generated constructor stub
		this.tuwtm = waterTank;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0; i<10;i++) {
			if (done) {
				System.out.println(Thread.currentThread().getName()+"Thread terminated");
				break;
			}
			this.tuwtm.OutflowLiters(1000.00);
			try {
				Thread.sleep(2000 );
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
