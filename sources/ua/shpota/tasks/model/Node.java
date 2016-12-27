package ua.shpota.tasks.model;

import java.io.Serializable;

public class Node implements Serializable {

    private final Task currentTask;
    private Node next;

    /**
     * Constructs node
     *
     * @param task
     */
    public Node(Task task) {
        if (task == null) {
            throw new NullPointerException("The task of the node must be specified.");
        }

        currentTask = task;
    }

    /**
     * Gets task
     *
     * @return currentTask
     */
    public Task getCurrentTask() {
        return currentTask;
    }

    /**
     * Sets next task
     *
     * @param next
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * Gets next task
     *
     * @return next
     */
    public Node getNext() {
        return next;
    }
}