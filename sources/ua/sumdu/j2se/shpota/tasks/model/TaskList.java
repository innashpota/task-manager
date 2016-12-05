package ua.sumdu.j2se.shpota.tasks.model;

import java.util.Iterator;

public abstract class TaskList implements Iterable<Task> {
    
    //Metod, shcho dodaye do spysku vkazanu zadachu
    abstract void add(Task task);
    
    /*
     * Metod, shcho vydalyaye zadachu iz spysku i povertaye istynu, 
     * yakshcho taka zadacha bula u spysku
     */
    abstract boolean remove(Task task);
    
    //Metod, shcho povertaye kil?kist? zadach u spysku
    abstract int size();
    
    //Metod, shcho povertaye zadachu, yaka znakhodyt?sya na vkazanomu mistsi
    abstract Task getTask(int index);
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        
        for (Task task : this) {
            sb.append(task).append("\n");
        }
        
        sb.append("]");
        
        return sb.toString();
    }
    
    @Override
    public int hashCode() {
        int constant = 31;
        int result = 0;
        
        result = constant * size();
        
        for (Task task : this) {
            result = result * task.hashCode();
        }
        
        return result;
    }
    
    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        
        if (anObject == null) {
            return false;
        }
        
        if (!getClass().equals(anObject.getClass())) {
            return false;
        }
        
        TaskList anotherTaskList = (TaskList) anObject;
        
        if (size() != anotherTaskList.size()) {
            return false;
        }
        
        Iterator<Task> iterator = this.iterator();
        Iterator<Task> anotherIterator = anotherTaskList.iterator();
        while (iterator.hasNext()) {
            Task currentTask = iterator.next();
            Task anotherTask = anotherIterator.next();
            if (!currentTask.equals(anotherTask)) {
                 return false;
            }
        }
        
        return true;
    }
}