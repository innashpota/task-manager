package ua.sumdu.j2se.shpota.tasks;

public class ArrayTaskList {
    
    private Task[] sourceListTask = new Task[0];
    
    //Metod, shcho dodaye do spysku vkazanu zadachu
    public void add(Task task) {
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
        return sourceListTask[index];
    }
    
    /*
     * Metod, shcho povertaye pidmnozhynu zadach, yaki zaplanovani na vykonannya 
     * khocha b raz pislya chasu from i ne piznishe nizh to
     */
    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList destinationList = new ArrayTaskList();
        
        for (int i = 0; i < sourceListTask.length; i++) {
            Task task = sourceListTask[i];
            int nextTimeAfter = task.nextTimeAfter(from);
            
            if (nextTimeAfter != -1 && nextTimeAfter <= to) {
                destinationList.add(task);
            }
        }
        return destinationList;
    }
}