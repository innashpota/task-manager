package ua.sumdu.j2se.shpota.tasks.model;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.HashSet;
import java.util.Set;

public class TaskModel implements TableModel{
    private Set<TableModelListener> listeners = new HashSet<>();
    private TaskList list;

    public TaskModel(TaskList list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Title";
            case 1:
                return "Time";
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Task task = list.getTask(rowIndex);
        switch (columnIndex) {
            case 0:
                return task.getTitle();
            case 1:
                return task.isRepeated() ? ("" + task.getStartTime() + " - " + task.getEndTime()) : task.getTime();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
