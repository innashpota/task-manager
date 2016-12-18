package ua.sumdu.j2se.shpota.tasks.model;

import java.io.File;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;

public class Main {
    public static void main(String[] var0) throws IOException, InterruptedException {
        Date time = new Date();
        Date start = new Date(time.getTime() - 24*60*60*1000);
        Date end = new Date(time.getTime() + 24*60*60*1000);

        Task task1 = new Task("1 \"asd\"", time);
        task1.setActive(true);
        Task task2 = new Task("2", start, end, 62*1000);
        Task task3 = new Task("3", start);
        Task task4 = new Task("4", end);

        LinkedTaskList list = new LinkedTaskList();
        list.add(task1);
        list.add(task2);
        list.add(task3);
        list.add(task4);

        PipedInputStream inputStream = new PipedInputStream();
        new PipedOutputStream(inputStream);

        File file = new File("tasksList.txt");
        TaskIO.writeText(list, file);

        LinkedTaskList result = new LinkedTaskList();
        TaskIO.readText(result, file);

        System.out.println(list.equals(result));

        Iterator iterator = result.iterator();
        while(iterator.hasNext()) {
            Task task = (Task)iterator.next();
            System.out.println(task);
        }
    }
}
