package ua.sumdu.j2se.shpota.tasks;

public class LinkedTaskList extends TaskList {

    //Zminni klasu
    private int size = 0;
    private Node first;
    private Node last;
    
    //Metod, shcho dodaye do spysku vkazanu zadachu
    public void add(Task task) {
        if (task == null) {
            throw new NullPointerException("The task of the node must be specified.");
        }
        
        //?
    };
    
    /*
     * Metod, shcho vydalyaye zadachu iz spysku i povertaye istynu, 
     * yakshcho taka zadacha bula u spysku
     */
    public boolean remove(Task task) {
        return true;
    };
    
    //Metod, shcho povertaye kil?kist? zadach u spysku
    public int size() {
        return 0;
    };
    
    //Metod, shcho povertaye zadachu, yaka znakhodyt?sya na vkazanomu mistsi
    public Task getTask(int index) {
        return null;
    };
}