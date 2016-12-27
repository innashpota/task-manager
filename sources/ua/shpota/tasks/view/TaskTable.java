package ua.shpota.tasks.view;

import ua.shpota.tasks.model.Task;
import ua.shpota.tasks.model.TasksModel;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;

/**
 * Table.
 * Class extends {@link AbstractTableModel}
 */
public class TaskTable extends AbstractTableModel {
    private static final SimpleDateFormat DATE_FORMAT =
            new SimpleDateFormat("[yyyy-MM-dd HH:mm]");

    private TasksModel model;

    /**
     * Constructs table of tasks
     *
     * @param model
     */
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
        String columnTitle;
        if (columnIndex == 0) {
            columnTitle = "Title";
        } else if (columnIndex == 1) {
            columnTitle = "Time";
        } else {
            throw new IllegalArgumentException("Table has only two columns.");
        }

        return columnTitle;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Task task = model.getTask(rowIndex);
        String columnValue;
        if (columnIndex == 0) {
            columnValue = task.getTitle();
        } else if (columnIndex == 1) {
            columnValue = task.isRepeated() ?
                    DATE_FORMAT.format(task.getStartTime()) + " - " +
                            DATE_FORMAT.format(task.getEndTime())
                    : DATE_FORMAT.format(task.getTime());
        } else {
            throw new IllegalArgumentException("Table has only two columns.");
        }

        return columnValue;
    }
}
