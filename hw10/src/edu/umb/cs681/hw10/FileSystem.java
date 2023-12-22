package edu.umb.cs681.hw10;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.atomic.AtomicReference;


public class FileSystem implements Runnable{
    private LinkedList<Directory> rootsDirs;
    private final AtomicBoolean done = new AtomicBoolean(true);
    static final AtomicReference<FileSystem> fileSystem = new AtomicReference<>(); 
    
    public static FileSystem getFileSystem(){
    	fileSystem.updateAndGet(currentFileSystem -> {
            if (currentFileSystem == null) {
                return new FileSystem();
            } else {
                return currentFileSystem;
            }
        });

        return fileSystem.get();


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
	

	

	@Override
	public void run() {
		while(done.get()) {
			Directory prjRoot = new Directory(null, "prjRoot", 999, LocalDateTime.now());
	        Directory src = new Directory(prjRoot, "src", 1000, LocalDateTime.now());
	        Directory lib = new Directory(prjRoot, "lib", 1001, LocalDateTime.now());
	        Directory test = new Directory(prjRoot, "test", 1002, LocalDateTime.now());
	        File x = new File(prjRoot, "x", 1003, LocalDateTime.now());
	        File a = new File(src, "a", 1004, LocalDateTime.now());
	        File b = new File(src, "b", 1005, LocalDateTime.now());
	        File c = new File(lib, "c", 1006, LocalDateTime.now());
	        Directory src_1 = new Directory(test, "src", 1006, LocalDateTime.now());
	        Link y = new Link(prjRoot, "y", 1008, LocalDateTime.now(),src_1 );
	        File d = new File(src_1, "d", 1007, LocalDateTime.now());
	        FileSystem fileSystem = FileSystem.getFileSystem();
	        fileSystem.appendRootDir(prjRoot);
	        prjRoot.appendChild(src);
	        prjRoot.appendChild(lib);
	        prjRoot.appendChild(test);
	        prjRoot.appendChild(x);
	        prjRoot.appendChild(y);
	        src.appendChild(a);
	        src.appendChild(b);
	        lib.appendChild(c);
	        test.appendChild(src_1);
	        src_1.appendChild(d);
	        
	        System.out.println("Getting children of root : ");
	        System.out.println("Child : " +fileSystem.getRootsDirs().get(0).getChildren().get(0).getName());
	        System.out.println("Child : " +fileSystem.getRootsDirs().get(0).getChildren().get(1).getName());
	        System.out.println("Child : " +fileSystem.getRootsDirs().get(0).getChildren().get(2).getName());
	        
			
		}
	}
	
	public void firstTermination() {
        done.set(false);
    }
	
	public static void main (String []args) {
		List<Thread> threadsList = new LinkedList<>();
		List<FileSystem> fileSystem=new LinkedList<>();
		for(int i=0;i<7;i++) {
			FileSystem handler=new FileSystem();
			fileSystem.add(handler);
			Thread thread = new Thread(handler);
			threadsList.add(thread);
			thread.start();
			System.out.println("Running threads..."+thread.getId());

		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (FileSystem client : fileSystem) {
			System.out.println("stopping thread..." + client.hashCode());
			client.firstTermination();
		}
		
		for (Thread t: threadsList ) {
			
				t.interrupt();
		
		}


	}
	
	
}
