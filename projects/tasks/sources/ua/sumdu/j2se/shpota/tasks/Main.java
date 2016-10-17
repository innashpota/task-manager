package ua.sumdu.j2se.shpota.tasks;

public class Main {
    public static void main(String[] args) {
        Task task = new Task("1",1);
        Task task1 = new Task("2",1);
        Task task2 = new Task("3",1);
        //System.out.println("######Task: " + task);
        ArrayTaskList list = new ArrayTaskList();
        list.add(task);
        list.add(task1);
        list.add(task2);
        for(int i = 0; i < list.size(); i++){
            System.out.println("Task " + list.getTask(i));
        }
        
        System.out.println(" Delete task from list: " + list.remove(task1));
        for(int i = 0; i < list.size(); i++){
            System.out.println("Task " + list.getTask(i));
        }
    }
}