package ua.sumdu.j2se.shpota.tasks;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayTaskListIterator implements Iterator<Task> {
    
    private ArrayTaskList current;
    private int position = -1;
    private boolean currentElementRemove = false;
    
    public ArrayTaskListIterator(ArrayTaskList current) {
        this.current = current;
    }
    
    @Override
    public boolean hasNext() {
        return position != current.size() - 1;
    }
    
    @Override
    public Task next() {
        if (position >= current.size() - 1) {
            throw new NoSuchElementException("The iteration has no more elements");
        }
        
        position++;
        currentElementRemove = true;
        return current.getTask(position);
    }
    
    @Override
    public void remove() {
        if (position < 0 || !currentElementRemove) {
            throw new IllegalStateException("The next method has not yet been called, " + 
                    "or the remove method has already been called after the last call to the next method");
        }
        
        if (currentElementRemove) {
            current.remove(current.getTask(position));
        }
        
        position--;
        currentElementRemove = false;
    }
}