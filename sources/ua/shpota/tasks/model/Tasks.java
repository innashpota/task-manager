package ua.shpota.tasks.model;

import java.util.*;

public class Tasks {

    /**
     * Returns a subset of the tasks that are scheduled to perform
     * at least once after from time and no later than to.
     *
     * @param tasks
     * @param from
     * @param to
     * @return destinationList
     */
    public static Iterable<Task> incoming(Iterable<Task> tasks, Date from, Date to) {
        if (tasks == null || from == null || to == null) {
            throw new IllegalArgumentException("Task, start date and end date can not be null");
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

    /**
     * Building calendar tasks in a given period.
     *
     * @param tasks
     * @param start
     * @param end
     * @return calendar
     */
    public static SortedMap<Date, Set<Task>> calendar(Iterable<Task> tasks, Date start, Date end) {
        if (tasks == null || start == null || end == null) {
            throw new NullPointerException("Task, start date and end date can not be null");
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
