package edu.umb.cs681.hw11.fs;

import java.time.LocalDateTime;
import java.util.LinkedList;

import edu.umb.cs681.hw11.fs.util.FileCrawlingVisitor;

public class Client {
	
	static LinkedList<File> sharedlist = new LinkedList<>();
	static LinkedList<Thread> tList = new LinkedList<>();
	
	
	public static void tree() {
		 LinkedList<FileCrawlingVisitor> threadLocal = new LinkedList<>();
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
        FileCrawlingVisitor fcv = new FileCrawlingVisitor();
        prjRoot.accept(fcv);
        System.out.println(fcv.getFiles()+"Files found by the thread :"+Thread.currentThread().getName());
        threadLocal.add(fcv);
        sharedlist.addAll(prjRoot.getFiles());
        
	}

    public static void main(String[] args) {
    	for(int i=0;i<5;i++) {
			Thread thread=new Thread(()->tree());
			thread.start();
			tList.add(thread);
			
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		FileSystem.setDone();
		for(Thread t:tList) {
			t.interrupt();
			System.out.println("Thread terminated");
		}
		System.out.println("Shared list contents :"+ sharedlist);
    }
}
