package edu.umb.cs681.hw13.ThreadUnsafe;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;



import edu.umb.cs681.hw13.ThreadSafe.WaterTank;

public class ThreadUnsafeWaterTankMonitor implements WaterTank {
	private double tankStatus = 0;
	static LinkedList<IntakeRunnable> depositList = new LinkedList<>();
	static LinkedList<OutflowRunnable> withdrawList = new LinkedList<>();

//	private ReentrantLock lock = new ReentrantLock();
//	private Condition minimumWaterLevel = lock.newCondition();
//	private Condition sufficientWaterLevel = lock.newCondition();
	static LinkedList<Thread> depositThreads = new LinkedList<>();
	static LinkedList<Thread> withdrawThreads = new LinkedList<>();
	@Override
	public void IntakeLiters(double liters) {
		// TODO Auto-generated method stub
//		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().threadId() + 
					" (d): current tankStatus: " + tankStatus);
			while(tankStatus >= 3000){
				System.out.println(Thread.currentThread().threadId() + 
						" (d): await(): Balance exceeds the upper limit.");
//				sufficientWaterLevel.await();
			}
			tankStatus += liters;
			System.out.println(Thread.currentThread().threadId() + 
					" (d): new tankStatus: " + tankStatus);
//			minimumWaterLevel.signalAll();
		}
		catch (Exception exception){
			exception.printStackTrace();
		}
		finally{
//			lock.unlock();
			System.out.println("Lock released");
		}
		
	}

	@Override
	public void OutflowLiters(double liters) {
		// TODO Auto-generated method stub
//		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().threadId() + 
					" (w): current tank Status: " + tankStatus);
			while(tankStatus <= 0){
				System.out.println(Thread.currentThread().threadId() + 
						" (w): await(): Low water level");
//				minimumWaterLevel.await();
			}
			tankStatus -= liters;
			System.out.println(Thread.currentThread().threadId() + 
					" (w): new tankStatus: " + tankStatus);
//			sufficientWaterLevel.signalAll();
		}
		catch (Exception exception){
			exception.printStackTrace();
		}
		finally{
//			lock.unlock();
			System.out.println("Lock released");
		}
		
	}

	@Override
	public double getTankStatus() {
		return this.tankStatus;
	}
	


	
	
	
	public static void main(String[] args) {


		ThreadUnsafeWaterTankMonitor waterTankMonitor = new ThreadUnsafeWaterTankMonitor();
		
		
		for (int i = 0; i < 5; i++) {
			IntakeRunnable depositRunnable = new IntakeRunnable(waterTankMonitor);
			OutflowRunnable withdrawRunnable = new OutflowRunnable(waterTankMonitor);
			
//			IntakeRunnable depositRunnable = new IntakeRunnable(waterTankMonitor);
//			OutflowRunnable withdrawRunnable = new OutflowRunnable(waterTankMonitor);
			depositList.add(depositRunnable);
			withdrawList.add(withdrawRunnable);
		}
		for (int i = 0; i < 5; i++) {
			depositThreads.add(new Thread(depositList.get(i)));
			withdrawThreads.add(new Thread(withdrawList.get(i)));
		}
		for (int i = 0; i < 5; i++) {
			depositThreads.get(i).start();
			withdrawThreads.get(i).start();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < 5; i++) {
			depositList.get(i).setDone();
			withdrawList.get(i).setDone();
			depositThreads.get(i).interrupt();
			withdrawThreads.get(i).interrupt();
		}

	}
	

}
