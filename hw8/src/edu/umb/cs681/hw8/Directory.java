package edu.umb.cs681.hw8;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement{
    private LinkedList<FSElement> children;
    public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
    }

    @Override
    public boolean isDirectory() {
        return true;
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

}
