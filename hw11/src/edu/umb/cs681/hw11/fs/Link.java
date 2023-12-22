package edu.umb.cs681.hw11.fs;


import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public class Link extends FSElement{
	ReentrantLock lock = new ReentrantLock();
    private FSElement target;
    public Link(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target) {
        super(parent, name, size, creationTime);
        this.target = target;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public void accept(FSVisitor visitor) {
		lock.lock();
		try {
			if(FileSystem.done.get())	{
				System.out.println("Thread terminated");
				return;
			}else {
        visitor.visit(this);}
    }finally {
    	lock.unlock();}
    }

    public FSElement getTarget() {
        return target;
    }
}
