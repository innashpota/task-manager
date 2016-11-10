package ua.sumdu.j2se.shpota.tasks;

import java.util.Date;
import java.util.Set;
import java.util.SortedMap;

public class Tasks{
    /*
     * Metod, shcho povertaye pidmnozhynu zadach, yaki zaplanovani na vykonannya
     * khocha b raz pislya chasu from i ne piznishe nizh to
     */
    public static Iterable<Task> incoming(Iterable<Task> tasks, Date from, Date to) {
        if (tasks == null || from == null || to == null) {
            throw new NullPointerException ("Task, start date and end date can not be null");
        }

        TaskList destinationList = new ArrayTaskList();

        for (Task task : tasks) {
            Date nextTimeAfter = task.nextTimeAfter(from);

            if (nextTimeAfter != null && (nextTimeAfter.before(to) || nextTimeAfter.equals(to))) {
                destinationList.add(task);
            }
        }
        return destinationList;
    }
}
