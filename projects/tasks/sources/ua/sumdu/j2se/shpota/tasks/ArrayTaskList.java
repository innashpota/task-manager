package ua.sumdu.j2se.shpota.tasks;

import java.util.Iterator;
import java.util.Date;

public class ArrayTaskList extends TaskList {
    
    private Task[] sourceListTask = new Task[0];
    
    @Override
    public void add(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("The task must be specified.");
        }
        
        int lengthOfSource = sourceListTask.length;
        Task[] destinationListTask = new Task[lengthOfSource + 1];
        System.arraycopy(sourceListTask, 0, destinationListTask, 0, lengthOfSource);
        destinationListTask[destinationListTask.length - 1] = task;
        sourceListTask = destinationListTask;
    }
    
    @Override
    public boolean remove(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("The task must be specified.");
        }
        
        int lengthOfSource = sourceListTask.length;
        Task[] destinationListTask = new Task[lengthOfSource - 1];
        
        for (int k = 0; k < lengthOfSource; k++) {
            if (sourceListTask[k].equals(task)) {
                System.arraycopy (sourceListTask, 0, destinationListTask, 0, k);
                System.arraycopy (sourceListTask, k + 1, destinationListTask, k, 
                                    lengthOfSource - k - 1);
                sourceListTask = destinationListTask;
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int size() {
        return sourceListTask.length;
    }
    
    @Override
    public Task getTask(int index) {
        if (index > sourceListTask.length) {
            throw new ArrayIndexOutOfBoundsException("The task of index = " + index + " does not exist.");
        }
        
        return sourceListTask[index];
    }
    
    @Override
    public Iterator<Task> iterator() {
        return new ArrayTaskListIterator(this);
    }
}