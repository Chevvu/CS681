package edu.umb.cs681.hw8;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem {
    private LinkedList<Directory> rootsDirs;
    private static FileSystem fileSystem = null;
    private FileSystem(){}
	private static ReentrantLock lock = new ReentrantLock();
    public static FileSystem getFileSystem(){
    	lock.lock();
		try {
        if(fileSystem == null){
            fileSystem = new FileSystem();
        }
        return fileSystem;
		} finally {
			lock.unlock();

		}
    }

    public LinkedList<Directory> getRootsDirs() {
        return this.rootsDirs;
    }
    public void appendRootDir(Directory root){
        if(this.rootsDirs == null){
            this.rootsDirs = new LinkedList<Directory>();
        }
        this.rootsDirs.add(root);
    }
    
	public static void main (String []args) {
		List<Thread> threadsList = new LinkedList<>();
		for(int i=0;i<7;i++) {
			Thread thread=new Thread(new Runnable(){
				@Override
	            public void run() {
					FileSystem instanceOne=FileSystem.getFileSystem();
					System.out.println("hashcode : "+instanceOne.hashCode());
				}
			});
			threadsList.add(thread);
			thread.start();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (Thread t: threadsList ) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}


	}
}
