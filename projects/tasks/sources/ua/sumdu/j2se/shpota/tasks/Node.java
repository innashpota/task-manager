package ua.sumdu.j2se.shpota.tasks;

public class Node {

    //Zminni klasu
    private Task currentTask;
    private Node next;
    
    //Konstruktor uzla
    public Node(Task task, Node next) {
        if (task == null) {
            throw new NullPointerException("The task of the node must be specified.");
        }
        
        currentTask = task;
        this.next = next;
    }
    
    //Vstanovlennya zadachi
    public void setTask(Task task) {
        if (task == null) {
            throw new NullPointerException("The task of the node must be specified.");
        }
        
        currentTask = task;
    }
    
    //Zchytuvannya zadachi
    public Task getTask() {
        return currentTask;
    }
    
    //Vstanovlennya nastupnoyi zadachi
    public void setNextTask(Node next) {
        this.next = next;
    }
    
    //Zchytuvannya nastupnoyi zadachi
    public Node getNextTask() {
        return next;
    }
}