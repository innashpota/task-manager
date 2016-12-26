package ua.sumdu.j2se.shpota.tasks.model;

import java.util.Iterator;

public abstract class TaskList implements Iterable<Task> {

    /**
     * Adds task to the list of task
     *
     * @param task
     */
    abstract void add(Task task);

    /**
     * Removes task from the list of task and
     * returns true if this task was listed
     *
     * @param task
     * @return true/false
     */
    abstract boolean remove(Task task);

    /**
     * Returns task index in the list of task
     *
     * @return list.length
     */
    abstract int size();

    /**
     * Returns a task that is at the specified location
     *
     * @param index
     * @return task
     */
    abstract Task getTask(int index);

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");

        for (Task task : this) {
            sb.append(task).append("\n");
        }

        sb.append("]");

        return sb.toString();
    }

    @Override
    public int hashCode() {
        int constant = 31;
        int result = 0;

        result = constant * size();

        for (Task task : this) {
            result = result * task.hashCode();
        }

        return result;
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }

        if (anObject == null) {
            return false;
        }

        if (!getClass().equals(anObject.getClass())) {
            return false;
        }

        TaskList anotherTaskList = (TaskList) anObject;

        if (size() != anotherTaskList.size()) {
            return false;
        }

        Iterator<Task> iterator = this.iterator();
        Iterator<Task> anotherIterator = anotherTaskList.iterator();
        while (iterator.hasNext()) {
            Task currentTask = iterator.next();
            Task anotherTask = anotherIterator.next();
            if (!currentTask.equals(anotherTask)) {
                return false;
            }
        }

        return true;
    }
}