package ua.sumdu.j2se.shpota.tasks;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedTaskListIterator implements Iterator<Task> {
    
    private LinkedTaskList current;
    private int position = -1;
    private boolean currentElementRemove = false;
    
    public LinkedTaskListIterator(LinkedTaskList current) {
        this.current = current;
    }
    
    @Override
    public boolean hasNext() {
        return position != current.size() - 1;
    }
    
    @Override
    public Task next() {
        if (position >= current.size() - 1) {
            throw new NoSuchElementException();
        }
        
        position++;
        currentElementRemove = true;
        return current.getTask(position);
    }
    
    @Override
    public void remove() {
        if (position < 0)
            throw new IllegalStateException();

        if (currentElementRemove) {
            current.remove(current.getTask(position));
        }
        position--;
    }
}