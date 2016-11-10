package ua.sumdu.j2se.shpota.tasks;

import java.util.Date;
import java.util.Iterator;

public class Main {
    
    public static void main(String[] args) {
        Date time = new Date();
        Date start = new Date(time.getTime() - 1000*60*60*24);
        Date end = new Date(time.getTime() + 1000*60*60*24);
        Task t = new Task("1", time);
        Task t1 = new Task("2",start,end,3600);
        Task t2 = new Task("3",start);
        Task t3 = new Task("4",end);
        
        LinkedTaskList list = new LinkedTaskList();
        list.add(t);
        list.add(t1);
        list.add(t2);
        list.add(t3);
        
        Iterator<Task> iterator = list.iterator();
        /*while (iterator.hasNext()) {
            Task curr = iterator.next();
            if (curr != t) {
               iterator.remove();
               iterator.remove();
            }
        }*/
        
        System.out.println("--------------------");
        for (Task task: list) {
            System.out.println("#### " + task);
        }
    }
}