package ua.sumdu.j2se.shpota.tasks;

import java.io.Serializable;
import java.util.*;

public class Tasks implements Serializable {
    /*
     * Metod, shcho povertaye pidmnozhynu zadach, yaki zaplanovani na vykonannya
     * khocha b raz pislya chasu from i ne piznishe nizh to
     */
    public static Iterable<Task> incoming(Iterable<Task> tasks, Date from, Date to) {
        if (tasks == null || from == null || to == null) {
            throw new IllegalArgumentException ("Task, start date and end date can not be null");
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

    /*
     * Metod, yakyy bude buduvaty kalendar zadach na zadanyy period - tablytsyu,
     * de kozhniy dati vidpovidaye mnozhyna zadach, shcho mayut? buty vykonani v
     * tsey chas, pry chomu odna zadacha mozhe zustrichatys? vidpovidno do dekil?kokh
     * dat, yakshcho vona maye buty vykonana dekil?ka raziv za vkazanyy period.
     */
    public static SortedMap<Date, Set<Task>> calendar(Iterable<Task> tasks, Date start, Date end) {
        if (tasks == null || start == null || end == null) {
            throw new NullPointerException ("Task, start date and end date can not be null");
        }

        SortedMap<Date, Set<Task>> calendar = new TreeMap<>();

        for (Task task : tasks) {
            Date date = task.nextTimeAfter(start);

            while (date != null && (date.before(end) || date.equals(end))) {
                Set<Task> currentSet = calendar.get(date);
                if (currentSet != null) {
                    currentSet.add(task);
                } else {
                    currentSet = new HashSet<>();
                    currentSet.add(task);
                    calendar.put(date, currentSet);
                }
                date = task.nextTimeAfter(date);
            }
        }

        return calendar;
    }
}
