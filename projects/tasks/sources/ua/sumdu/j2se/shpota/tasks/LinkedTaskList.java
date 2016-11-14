package ua.sumdu.j2se.shpota.tasks;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedTaskList extends TaskList {

    private int size = 0;
    private Node first;
    
    @Override
    public void add(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("The task must be specified.");
        }
        
        Node newNode = new Node(task);
        
        if (first == null) {
            first = newNode;
        } else {
            Node current = first;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }
    
    @Override
    public boolean remove(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("The task must be specified.");
        }
        
        if (first != null) {
            Node current = first;
            Node previous = null;
            
            if (first.getCurrentTask().equals(task)) {
                first = current.getNext();
                size--;
                return true;
            }
              
            while (!current.getCurrentTask().equals(task)) {
                if (current.getNext() == null) {
                    return false;
                }
                previous = current;
                current = current.getNext();
            }
            
            if (previous != null) {
                previous.setNext(current.getNext());
                size--;
                return true;
            }
        }
        
        
        return false;
    }
    
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public Task getTask(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("The task of index = " + index + " does not exist.");
        }
        
        Node current = first;
        
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getCurrentTask();
    }
    
    @Override
    public Iterator<Task> iterator() {
        return new LinkedTaskListIterator();
    }
    
    private class LinkedTaskListIterator implements Iterator<Task> {
        
        private Node previous = null;
        private Node lastReturned =  null;
        private boolean nextInvoked = false;
        
        @Override
        public boolean hasNext() {
            if (lastReturned == null) {
                return first != null;
            }
            
            return lastReturned.getNext() != null;
        }

        @Override
        public Task next() {
            if (lastReturned == null) {
                lastReturned = first;
            } else {
                previous = lastReturned;
                lastReturned = lastReturned.getNext();
            }
 
            if (lastReturned == null) {
                throw new NoSuchElementException("The iteration has no more elements");
            }
            
            nextInvoked = true;
            
            return lastReturned.getCurrentTask();
        }

        @Override
        public void remove() {
            if (!nextInvoked) {
                throw new IllegalStateException("The next method has not yet been called, " + 
                        "or the remove method has already been called after the last call to the next method");
            }
            
            if (lastReturned == first) {
                first = lastReturned.getNext();
                lastReturned = null;
            } else {
                previous.setNext(lastReturned.getNext());
                lastReturned = previous;
            }
            
            size--;
            nextInvoked = false;
        }
    }
}