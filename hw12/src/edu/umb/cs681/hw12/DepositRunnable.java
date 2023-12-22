package edu.umb.cs681.hw12;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;

public class DepositRunnable implements Runnable {

	private AtomicBoolean done = new AtomicBoolean(false);
	private ThreadSafeBankAccount2 tsba;
	public AtomicBoolean getDone() {
		return this.done;
	}

	public DepositRunnable(ThreadSafeBankAccount2 bankAccount) {
		// TODO Auto-generated constructor stub
		this.tsba=bankAccount;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0; i<10;i++) {
			if (done.get()) {
				System.out.println(Thread.currentThread().getName()+"Thread terminated");
				break;
			}
			this.tsba.deposit(100);
			try {
				Thread.sleep( 2000 );
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
