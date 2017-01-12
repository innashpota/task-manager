package ua.shpota.tasks.view;

import ua.shpota.tasks.model.TasksModel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import static javax.swing.JOptionPane.*;

/**
 * Option dialog in windows closing
 * Class extends {@link WindowAdapter}
 */
public class DialogWindowAdapter extends WindowAdapter {
    private TasksModel model;

    public DialogWindowAdapter(TasksModel model) {
        this.model = model;
    }

    @Override
    public void windowClosing(WindowEvent event) {
        Object[] options = {"Yes", "No"};
        int rc = showOptionDialog(event.getWindow(),
                "Close window", "Confirmation",
                YES_NO_OPTION, QUESTION_MESSAGE,
                null, options, options[0]);
        if (rc == 0) {
            try {
                model.storeTasksModel();
            } catch (IOException exception) {
                showMessageDialog(null,
                        exception.getMessage(), "Warning",
                        WARNING_MESSAGE);
            }
            event.getWindow().setVisible(false);
            System.exit(0);
        }
    }
}
