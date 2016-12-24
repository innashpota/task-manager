package ua.sumdu.j2se.shpota.tasks.model;

import java.util.Observable;

public class TasksModel extends Observable {
    private TaskList list;

    public TasksModel (TaskList list) {
        this.list = list;

        super.setChanged();
        super.notifyObservers();
    }

    public TaskList getList() {
        return list;
    }

    public int getSize () {
        return list.size();
    }

    public Task getTask (int index) {
        return list.getTask(index);
    }

    public void add(Task task) {
        list.add(task);

        super.setChanged();
        super.notifyObservers();
    }

    public void remove (int index) {
        list.remove(getTask(index));

        super.setChanged();
        super.notifyObservers();
    }

    public Observable observable() {
        return this;
    }
}
