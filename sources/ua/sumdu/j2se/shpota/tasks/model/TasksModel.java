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

    /**
     * Loads file of task from model
     *
     * @return new TasksModel(TaskIO.loadFile())
     * @throws IOException
     */
    public static TasksModel loadTasksModel() throws IOException {
        return new TasksModel(TaskIO.loadFile());
    }

    /**
     * Stores list of task into text file
     *
     * @throws IOException
     */
    public static void storeTasksModel() throws IOException {
        TaskIO.storeFile(getList());
    }

    /**
     * Gets list of task
     *
     * @return list
     */
    public static TaskList getList() {
        return list;
    }

    /**
     * Gets list size
     *
     * @return list.size()
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Gets a task that is at the specified location
     *
     * @param index
     * @return list.getTask(index)
     */
    public Task getTask(int index) {
        return list.getTask(index);
    }

    /**
     * Adds non repeated task
     *
     * @param title
     * @param time
     * @param active
     */
    public void addTask(String title, Date time, boolean active) {
        Task task = new Task(title, time);
        add(active, task);
    }

    /**
     * Adds repeated task
     *
     * @param title
     * @param start
     * @param end
     * @param interval
     * @param active
     */
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

    /**
     * Removes a task that is at the specified location
     *
     * @param index
     */
    public void remove(int index) {
        list.remove(getTask(index));

        super.setChanged();
        super.notifyObservers();
    }

    /**
     * Notify the types of model changes
     *
     * @return this
     */
    public Observable observable() {
        return this;
    }
}
