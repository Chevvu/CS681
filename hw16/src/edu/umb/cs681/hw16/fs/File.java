package edu.umb.cs681.hw16.fs;



import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public class File extends FSElement{
	ReentrantLock lock = new ReentrantLock();
    public File(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public void accept(FSVisitor visitor) {
    	
		lock.lock();
		try {		if(FileSystem.done.get())	{
			System.out.println("Thread terminated");
			return ;
		}else {
        visitor.visit(this);}
    }finally {
    	lock.unlock();}
    }
}
