package edu.umb.cs681.hw16.fs;


public interface FSVisitor {
    public abstract void visit(Link link);
    public abstract void visit(Directory directory);
    public abstract void visit(File file);
}
