package ua.sumdu.j2se.shpota.tasks.model;

import java.io.Serializable;

public class Node implements Serializable {

    private final Task currentTask;
    private Node next;

    /**
     * Constructor node
     * @param task
     */
    public Node(Task task) {
        if (task == null) {
            throw new NullPointerException("The task of the node must be specified.");
        }
        
        currentTask = task;
    }

    /**
     * Getting task
     * @return
     */
    public Task getCurrentTask() {
        return currentTask;
    }

    /**
     * Setting next task
     * @param next
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * Getting next task
     * @return next
     */
    public Node getNext() {
        return next;
    }
}