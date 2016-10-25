package ua.sumdu.j2se.shpota.tasks;

abstract class TaskList {
    
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
    
    /*
     * Metod, shcho povertaye pidmnozhynu zadach, yaki zaplanovani na vykonannya 
     * khocha b raz pislya chasu from i ne piznishe nizh to
     */
    public TaskList incoming(int from, int to) {
        if (from < 0 || to < 0) {
            throw new IllegalArgumentException("The time from and time to must be non-negative number.");
        }
        
        TaskList destinationList = new ArrayTaskList();
        
        for (int i = 0; i < size(); i++) {
            Task task = getTask(i);
            int nextTimeAfter = task.nextTimeAfter(from);
            
            if (nextTimeAfter != -1 && nextTimeAfter <= to) {
                destinationList.add(task);
            }
        }
        return destinationList;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ \n");
        
        for (int i = 0; i < size(); i++) {
            if (i != size() - 1) {
                sb.append(getTask(i)).append("\n");
            } else {
                sb.append(getTask(i)).append("\n]");
            }
        }
        
        String s = sb.toString();
        
        return s;
    }
    
    @Override
    public int hashCode() {
        int constant = 31;
        int result = 0;
        
        result = constant * size();
        
        for (int i = 0; i < size(); i++) {
            result = result * getTask(i).hashCode();
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
        
        TaskList anotherTaskList = (TaskList)anObject;
        
        if (size() != anotherTaskList.size()) {
            return false;
        }
        
        for (int i = 0; i < size(); i++) {
            Task currentTaskList = getTask(i);
            Task currentAnotherTaskList = anotherTaskList.getTask(i);
            if (!currentTaskList.equals(currentAnotherTaskList)) {
                return false;
            }
        }
        
        return true;
    }
}