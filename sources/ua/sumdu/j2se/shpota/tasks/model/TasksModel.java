package ua.sumdu.j2se.shpota.tasks.model;

import java.util.Observable;

public class TasksModel extends Observable {
    private TaskList list;

    public TasksModel (TaskList list) {
        this.list = list;
        super.setChanged();
        super.notifyObservers();
    }

    public int getSize () {
        return list.size();
    }

    public Task getTask (int index) {
        return list.getTask(index);
    }

    public boolean remove (int index) {
        boolean remove = list.remove(getTask(index));

        super.setChanged();
        super.notifyObservers();
        return remove;
    }

    public Observable observable() {
        return this;
    }
}
