package edu.umb.cs681.hw072;
import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer  {
	private ReentrantLock lock  = new ReentrantLock();;
	private boolean done = false;
	
	public RunnableCancellablePrimeFactorizer(long dividend, long from, long to) {
		super(dividend, from, to);
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
		}
	}
	

	public static void main(String[] args) {
		RunnableCancellablePrimeFactorizer genFactors = new  RunnableCancellablePrimeFactorizer(100,2, (long)Math.sqrt(100));
		Thread thread = new Thread(genFactors);
		thread.start();
		genFactors.setDone();
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(genFactors.getPrimeFactors()+" These are the prime factors generated");
		
	}
}
