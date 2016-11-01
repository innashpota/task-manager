package ua.sumdu.j2se.shpota.tasks;

public class Main {
    
    public static void main(String[] args) {
        Task task = new Task("1",1);
        Task task1 = new Task("wigfkjdbcsn",1,10,3);
        Task task2 = new Task("1",1);
        
        ArrayTaskList list = new ArrayTaskList();
        list.add(task);
        list.add(task1);
        list.add(task2);
        
        for (Task t : list) {
            System.out.println(t);
        }
        
        Task t = new Task("101",1);
        Task t1 = new Task("wigfkjdbcn",1,10,3);
        Task t2 = new Task("3",120);
        
        LinkedTaskList list1 = new LinkedTaskList();
        list1.add(t);
        list1.add(t1);
        list1.add(t2);
        
        System.out.println(task.equals(new Object()));
        System.out.println("Hash code " + list1.hashCode());
        
        /*for(int i = 0; i < list.size(); i++){
            System.out.println("Task " + list.getTask(i));
            //System.out.println("Size of list " + list.size());
        }
        
        System.out.println(" Delete task from list: " + list.remove(new Task("4",1)));
        for(int i = 0; i < list.size(); i++){
            System.out.println("Task " + list.getTask(i));
        }*/
        
        //System.out.println(list.toString());
    }
}