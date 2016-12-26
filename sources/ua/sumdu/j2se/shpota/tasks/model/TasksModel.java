package ua.sumdu.j2se.shpota.tasks.model;

import java.io.IOException;
import java.util.Date;
import java.util.Observable;

public class TasksModel extends Observable {
    private static TaskList list;

    private TasksModel(TaskList list) {
        this.list = list;

        super.setChanged();
        super.notifyObservers();
    }

    public static TasksModel loadTasksModel() throws IOException {
        return new TasksModel(TaskIO.loadFile());
    }

    public static void storeTasksModel() throws IOException {
        TaskIO.storeFile(getList());
    }

    public static TaskList getList() {
        return list;
    }

    public int getSize() {
        return list.size();
    }

    public Task getTask(int index) {
        return list.getTask(index);
    }

    public void addTask(String title, Date time, boolean active) {
        Task task = new Task(title, time);
        add(active, task);
    }

    public void addTask(String title, Date start, Date end, int interval, boolean active) {
        Task task = new Task(title, start, end, interval);
        add(active, task);
    }

    private void add(boolean active, Task task) {
        task.setActive(active);
        list.add(task);

        super.setChanged();
        super.notifyObservers();
    }

    public void remove(int index) {
        list.remove(getTask(index));

        super.setChanged();
        super.notifyObservers();
    }

    public Observable observable() {
        return this;
    }
}
