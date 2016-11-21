package ua.sumdu.j2se.shpota.tasks;

import java.io.Serializable;

public class Node implements Serializable {

    private final Task currentTask;
    private Node next;
    
    //Konstruktor uzla
    public Node(Task task) {
        if (task == null) {
            throw new NullPointerException("The task of the node must be specified.");
        }
        
        currentTask = task;
    }
    
    //Zchytuvannya zadachi
    public Task getCurrentTask() {
        return currentTask;
    }
    
    //Vstanovlennya nastupnoyi zadachi
    public void setNext(Node next) {
        this.next = next;
    }
    
    //Zchytuvannya nastupnoyi zadachi
    public Node getNext() {
        return next;
    }
}