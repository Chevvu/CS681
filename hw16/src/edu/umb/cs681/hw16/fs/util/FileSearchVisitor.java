package edu.umb.cs681.hw16.fs.util;

import edu.umb.cs681.hw16.fs.*;

import java.util.LinkedList;

public class FileSearchVisitor implements FSVisitor {
   private String fileName;
   private LinkedList<File> foundFiles = new LinkedList<File>();
    public FileSearchVisitor(String fileName){
        this.fileName = fileName;
    }
    @Override
    public void visit(Link link) {
        return;
    }

    @Override
    public void visit(Directory directory) {
    return;
    }

    @Override
    public void visit(File file) {
    if(file.getName().equals(fileName)){
        foundFiles.add(file);
    }
    }

    public LinkedList<File> getFoundFiles() {
        return foundFiles;
    }
}
