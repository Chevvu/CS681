package edu.umb.cs681.hw15;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Observable<T> {
	private LinkedList<Observer<T>> observers = new LinkedList<>();
	ReentrantLock lockObservable = new ReentrantLock();
	public void addObserver(Observer<T> o) {
		observers.add(o);
	}

	public void clearObservers() {
		observers.clear();
		
	}
	public List<Observer<T>> getObservers(){
		return observers;
	}
	
	public int countObservers() {
		return observers.size();
		
	}
	public void removeObserver(Observer<T> o) {
		observers.remove(o);
	}

	public void notifyObservers(T event) {
		LinkedList<Observer<T>> observersLocal;
		lockObservable.lock();
		try {
			observersLocal = new LinkedList<>(observers);
		}finally {
			lockObservable.unlock();}
		observers.forEach( (observer)->{observer.update(this, event);} );
	}
	
}
