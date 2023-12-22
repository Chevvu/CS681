package edu.umb.cs681.hw15;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable<StockEvent> implements Runnable {
    Map<String, Double> map_ = new HashMap<>();
    ReentrantLock lockTQ = new ReentrantLock();
	static LinkedList<Thread> tList = new LinkedList<>();
	AtomicBoolean done = new AtomicBoolean(false);
	public void setDone() {
		done.set(true);
	}
    public void changeQuote(String ticker, double quote){
		lockTQ.lock();
		try {
        map_.put(ticker, quote);
		} finally {
			lockTQ.unlock();
		}
        notifyObservers(new StockEvent(ticker, quote));
    }

    public Map<String, Double> getMap_() {
        return map_;
    }
	@Override
	public void run() {
		while (true) {
			if (!done.get()) {
				
				StockEvent one = new StockEvent("AAPL", 194);
				TableObserver tableObserver = new TableObserver();
				StockQuoteObservable so = new StockQuoteObservable();
				so.addObserver(tableObserver);
				so.changeQuote("AAPL", 220);
			} else {
				System.out.println( Thread.currentThread().getName()+" Terminated ");
				break;
			}
		}

	}
	public static void main(String[] args) throws InterruptedException {
		StockQuoteObservable so = new StockQuoteObservable();

		for(int i=0;i<15;i++) {
			Thread thread=new Thread(so);
			thread.start();
			tList.add(thread);
			
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		so.setDone();
		for(Thread t:tList) {
			t.interrupt();
		}
	}


}
