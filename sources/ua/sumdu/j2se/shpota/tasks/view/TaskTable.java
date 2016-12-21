package ua.sumdu.j2se.shpota.tasks.view;

import ua.sumdu.j2se.shpota.tasks.model.Task;
import ua.sumdu.j2se.shpota.tasks.model.TasksModel;

import javax.swing.table.AbstractTableModel;

public class TaskTable extends AbstractTableModel {
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
}
