package edu.umb.cs681.hw10;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;


public abstract class FSElement {
    protected String name;
    protected int size;
    protected LocalDateTime creationTime;
    private Directory parent;
	private final static ReentrantLock lock = new ReentrantLock();

 
    public  FSElement(Directory parent, String name, int size, LocalDateTime creationTime){
        this.name = name;
        this.size = size;
        this.creationTime = creationTime;
        this.parent = parent;
    }
    public abstract boolean isDirectory();

    public String getName() {
        return name;
    }
    
    public static ReentrantLock Lock() {
    	return lock;
    }


    public int getSize() {
        return size;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public Directory getParent() {
        return parent;
    }
}
