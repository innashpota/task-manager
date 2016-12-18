package ua.sumdu.j2se.shpota.tasks.view;

import ua.sumdu.j2se.shpota.tasks.model.Task;
import ua.sumdu.j2se.shpota.tasks.model.TasksModel;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.HashSet;
import java.util.Set;

public class TaskTable implements TableModel {
    private Set<TableModelListener> listeners = new HashSet<>();
    private TasksModel model;

    public TaskTable(TasksModel model) {
        super();
        this.model = model;
    }

    @Override
    public int getRowCount() {
        return model.getSize();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int columnIndex) {
        if (columnIndex == 0) {
            return "Title";
        } else if (columnIndex == 1) {
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
        Task task = model.getTask(rowIndex);
        if (columnIndex == 0) {
            return task.getTitle();
        } else if (columnIndex == 1) {
            return task.isRepeated() ? ("" + task.getStartTime() + " - " +
                    task.getEndTime()) : task.getTime();
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
        listeners.remove(l);
    }
}
