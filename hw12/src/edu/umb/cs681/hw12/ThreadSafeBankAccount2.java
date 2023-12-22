package edu.umb.cs681.hw12;

import java.util.concurrent.locks.ReentrantLock;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;

public class ThreadSafeBankAccount2 implements BankAccount{
	static LinkedList<DepositRunnable> depositList = new LinkedList<>();
	static LinkedList<WithdrawRunnable> withdrawList = new LinkedList<>();
	private double balance = 0;
	private ReentrantLock lock = new ReentrantLock();
	private Condition sufficientFundsCondition = lock.newCondition();
	private Condition belowUpperLimitFundsCondition = lock.newCondition();
	static LinkedList<Thread> depositThreads = new LinkedList<>();
	static LinkedList<Thread> withdrawThreads = new LinkedList<>();

	
	
	
	public static void main(String[] args) {


		ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();
		
		for (int i = 0; i < 5; i++) {
			DepositRunnable depositRunnable = new DepositRunnable(bankAccount);
			WithdrawRunnable withdrawRunnable = new WithdrawRunnable(bankAccount);
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
			;
			withdrawThreads.get(i).interrupt();
		}

	}
	
	public void deposit(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().threadId() + 
					" (d): current balance: " + balance);
			while(balance >= 300){
				System.out.println(Thread.currentThread().threadId() + 
						" (d): await(): Balance exceeds the upper limit.");
				belowUpperLimitFundsCondition.await();
			}
			balance += amount;
			System.out.println(Thread.currentThread().threadId() + 
					" (d): new balance: " + balance);
			sufficientFundsCondition.signalAll();
		}
		catch (InterruptedException exception){
			exception.printStackTrace();
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}
	
	public void withdraw(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().threadId() + 
					" (w): current balance: " + balance);
			while(balance <= 0){
				System.out.println(Thread.currentThread().threadId() + 
						" (w): await(): Insufficient funds");
				sufficientFundsCondition.await();
			}
			balance -= amount;
			System.out.println(Thread.currentThread().threadId() + 
					" (w): new balance: " + balance);
			belowUpperLimitFundsCondition.signalAll();
		}
		catch (InterruptedException exception){
			exception.printStackTrace();
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}

	public double getBalance() { return this.balance; }

	
}
