package edu.umb.cs681.hw11.fs.util;

import edu.umb.cs681.hw11.fs.Directory;
import edu.umb.cs681.hw11.fs.FSVisitor;
import edu.umb.cs681.hw11.fs.File;
import edu.umb.cs681.hw11.fs.Link;

import java.util.LinkedList;

public class FileCrawlingVisitor implements FSVisitor {
    private LinkedList<File> files = new LinkedList<File>();
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
    files.add(file);
    }

    public LinkedList<File> getFiles() {
        return files;
    }
}
