package ua.sumdu.j2se.shpota.tasks;

public class ArrayTaskList extends TaskList {
    
    private Task[] sourceListTask = new Task[0];
    
    //Metod, shcho dodaye do spysku vkazanu zadachu
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
    
    /*
     * Metod, shcho vydalyaye zadachu iz spysku i povertaye istynu, 
     * yakshcho taka zadacha bula u spysku
     */
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
    
    //Metod, shcho povertaye kil?kist? zadach u spysku
    public int size() {
        return sourceListTask.length;
    }
    
    //Metod, shcho povertaye zadachu, yaka znakhodyt?sya na vkazanomu mistsi
    public Task getTask(int index) {
        if (index > sourceListTask.length) {
            throw new ArrayIndexOutOfBoundsException("The output index for the array boundary.");
        }
        
        return sourceListTask[index];
    }
}