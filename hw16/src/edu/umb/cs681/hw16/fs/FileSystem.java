package edu.umb.cs681.hw16.fs;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

public class FileSystem {
	public static AtomicBoolean done = new AtomicBoolean(false);
    private LinkedList<Directory> rootsDirs;
    private static FileSystem fileSystem = null;
    private FileSystem(){}
    public static FileSystem getFileSystem(){
        if(fileSystem == null){
            fileSystem = new FileSystem();
        }
        return fileSystem;
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
    
    public static void setDone() {
    	done.set(true);
    }
}
