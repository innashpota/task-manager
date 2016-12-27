package ua.shpota.tasks.controller;

import ua.shpota.tasks.model.TasksModel;
import ua.shpota.tasks.view.SwingTasksView;

import java.io.IOException;

/**
 * Controller.
 * Handles events of the form and update the model.
 */
public class TaskController {

    /**
     * Main class
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        createView(TasksModel.loadTasksModel());
    }

    private static void createView(TasksModel model) {
        SwingTasksView view = new SwingTasksView(model);
        view.createSwingView();
    }
}
