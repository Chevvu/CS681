package edu.umb.cs681.hw11.fs;

import java.time.LocalDateTime;

public abstract class FSElement {
    protected String name;
    protected int size;
    protected LocalDateTime creationTime;
    private Directory parent;
    public  FSElement(Directory parent, String name, int size, LocalDateTime creationTime){
        this.name = name;
        this.size = size;
        this.creationTime = creationTime;
        this.parent = parent;
    }
    public abstract boolean isDirectory();
    public abstract void accept(FSVisitor visitor);

    public String getName() {
        return name;
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
