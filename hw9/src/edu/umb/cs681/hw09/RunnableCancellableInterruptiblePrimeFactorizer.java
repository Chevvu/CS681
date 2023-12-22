package edu.umb.cs681.hw09;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellableInterruptiblePrimeFactorizer extends  RunnableCancellablePrimeFactorizer  {
	
	private boolean done = false;
	private final ReentrantLock lock = new ReentrantLock();

	public RunnableCancellableInterruptiblePrimeFactorizer(long dividend, long from, long to) {
		super(dividend, from, to);
		// TODO Auto-generated constructor stub
	}
	
	public void setDone(){
		lock.lock();
		try {
			done = true;
		}
		finally {
			lock.unlock();
		}
	}
	
	public void generatePrimeFactors() {
		long divisor = from;
	    while( dividend != 1 && divisor <= to ){
			lock.lock();
			try {
				if (done) {
					System.out.println("Stopped generating prime factors.");
					this.factors.clear();
					break;
				}
	    	if( divisor > 2 && isEven(divisor)) {
	    		divisor++;
	    		continue;
	    	}
		    if(dividend % divisor == 0) {
		        factors.add(divisor);
		        dividend /= divisor;
		    }else {
		    	if(divisor==2){ divisor++; }
		    	else{ divisor += 2; }
		    }
			}finally {
				lock.unlock();
			}

			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				System.out.println(e.toString());
				continue;
			}
		}
	}
	
	public static void main(String[] args) {
		RunnableCancellableInterruptiblePrimeFactorizer genFactors =
				new RunnableCancellableInterruptiblePrimeFactorizer(1000,2, (long)Math.sqrt(1000));
		Thread thread = new Thread(genFactors);
		thread.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		genFactors.setDone();
		thread.interrupt();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(genFactors.getPrimeFactors()+" These are the prime factors generated");
	}
	

}
