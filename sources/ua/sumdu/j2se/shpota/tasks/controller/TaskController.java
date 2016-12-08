package ua.sumdu.j2se.shpota.tasks.controller;

import ua.sumdu.j2se.shpota.tasks.view.SwingTasksListView;

import java.io.IOException;
import java.text.ParseException;

public class TaskController {
    public static void main (String[] args) throws IOException, ParseException {
        SwingTasksListView swingTaskView = new SwingTasksListView();
        swingTaskView.setVisible(true);
        swingTaskView.pack();
    }

}
