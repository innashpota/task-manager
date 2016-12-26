package ua.sumdu.j2se.shpota.tasks.controller;

import ua.sumdu.j2se.shpota.tasks.model.TasksModel;
import ua.sumdu.j2se.shpota.tasks.view.SwingTasksView;

import java.io.IOException;

public class TaskController {
    public static void main (String[] args) throws IOException {
        createView(TasksModel.loadTasksModel());
    }

    private static void createView (TasksModel model) {
        SwingTasksView view = new SwingTasksView(model);
        view.createSwingView();
    }
}
