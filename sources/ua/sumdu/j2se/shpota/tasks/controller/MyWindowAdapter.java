package ua.sumdu.j2se.shpota.tasks.controller;

import ua.sumdu.j2se.shpota.tasks.model.TaskIO;
import ua.sumdu.j2se.shpota.tasks.model.TasksModel;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class MyWindowAdapter extends WindowAdapter {
    private TasksModel model;

    public MyWindowAdapter (TasksModel model) {
        this.model = model;
    }
    @Override
    public void windowClosing(WindowEvent event) {
        Object[] options = {"Yes", "No"};
        int rc = JOptionPane.showOptionDialog(event.getWindow(),
                "Close window", "Confirmation",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        if (rc == 0) {
            try {
                TaskIO.storeTaskModel(model.getList());
            } catch (IOException exception) {
                JOptionPane.showMessageDialog(null,
                        exception.getMessage(), "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
            event.getWindow().setVisible(false);
            System.exit(0);
        }
    }
}
