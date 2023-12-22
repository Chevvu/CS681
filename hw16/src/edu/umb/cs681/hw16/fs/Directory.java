package edu.umb.cs681.hw16.fs;



import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;



public class Directory extends FSElement{
    private LinkedList<FSElement> children = new LinkedList<FSElement>();
    LinkedList<Directory> directory = new LinkedList<Directory>();
	LinkedList<File> file = new LinkedList<File>();
	ReentrantLock lock = new ReentrantLock();
	static LinkedList<Thread> tList = new LinkedList<>();
    public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public void accept(FSVisitor visitor) {
		lock.lock();
		try {
		if(FileSystem.done.get())	{
			System.out.println("Thread terminated");
			return ;
		}else {
		        visitor.visit(this);
		        for (FSElement e: getChildren()){
		            e.accept(visitor);}
        }
		}finally {
			lock.unlock();}
    }

    public void appendChild(FSElement child){
        if(children == null){
            children = new LinkedList<FSElement>();
        }
        children.add(child);
    }

    public LinkedList<FSElement> getChildren() {
        return children;
    }
    public int countChildren(){
        return this.children.size();
    }
    public LinkedList<Directory> getSubDirectories(){
        LinkedList<Directory> SubDirectories = new LinkedList<Directory>();
        for (FSElement child:getChildren()){
            if (child.isDirectory()){
                SubDirectories.add((Directory) child);
            }
        }
        return SubDirectories;
    }
    public LinkedList<File> getFiles(){
        LinkedList<File> files = new LinkedList<File>();
        for (FSElement child: getChildren()){
            if(child instanceof File){
                files.add((File) child);
            }
        }
        return files;
    }
    public int getTotalSize(){
        int total = 0;
        for(FSElement child: getChildren()){
            if(child.isDirectory()){
                total = total+((Directory)child).getTotalSize();
            }
            else {
                total = total + child.getSize();
            }
        }
        return total;
    }
    

    
//    public static void main(String[] args) {
//    	
//    	for(int i=0;i<15;i++) {
//			Thread thread=new Thread(so);
//			thread.start();
//			tList.add(thread);
//			
//		}
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//
//			e.printStackTrace();
//		}
//		so.setDone();
//		for(Thread t:tList) {
//			t.interrupt();
//		}
//	
//    	
//        Directory prjRoot = new Directory(null, "prjRoot", 999, LocalDateTime.now());
//        Directory src = new Directory(prjRoot, "src", 1000, LocalDateTime.now());
//        Directory lib = new Directory(prjRoot, "lib", 1001, LocalDateTime.now());
//        Directory test = new Directory(prjRoot, "test", 1002, LocalDateTime.now());
//        File x = new File(prjRoot, "x", 1003, LocalDateTime.now());
//        File a = new File(src, "a", 1004, LocalDateTime.now());
//        File b = new File(src, "b", 1005, LocalDateTime.now());
//        File c = new File(lib, "c", 1006, LocalDateTime.now());
//        Directory src_1 = new Directory(test, "src", 1006, LocalDateTime.now());
//        Link y = new Link(prjRoot, "y", 1008, LocalDateTime.now(),src_1 );
//        File d = new File(src_1, "d", 1007, LocalDateTime.now());
//        FileSystem fileSystem = FileSystem.getFileSystem();
//        fileSystem.appendRootDir(prjRoot);
//        prjRoot.appendChild(src);
//        prjRoot.appendChild(lib);
//        prjRoot.appendChild(test);
//        prjRoot.appendChild(x);
//        prjRoot.appendChild(y);
//        src.appendChild(a);
//        src.appendChild(b);
//        lib.appendChild(c);
//        test.appendChild(src_1);
//        src_1.appendChild(d);
//
//        FileCrawlingVisitor fcv = new FileCrawlingVisitor();
//        prjRoot.accept(fcv);
//        System.out.println(prjRoot.getFiles());
//	}

}
