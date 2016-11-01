package ua.sumdu.j2se.shpota.tasks;

import java.util.Iterator;

public class LinkedTaskList extends TaskList implements Iterable<Task> {

    private int size = 0;
    private Node first;
    
    @Override
    public void add(Task task) {
        if (task == null) {
            throw new NullPointerException("The task of the node must be specified.");
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
            throw new NullPointerException("The task of the node must be specified.");
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
        return new LinkedTaskListIterator(this);
    }
}