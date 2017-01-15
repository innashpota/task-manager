package ua.shpota.tasks.controller;

import ua.shpota.tasks.model.IncomingTasksModel;
import ua.shpota.tasks.model.TasksModel;
import ua.shpota.tasks.view.DisplayTrayIcon;
import ua.shpota.tasks.view.IncomingTasksView;
import ua.shpota.tasks.view.SwingTasksView;

import java.io.IOException;

/**
 * Controller.
 * Handles events of the form and update the model.
 */
public class TaskController {

    public static void main(String[] args) throws IOException {
        createView(TasksModel.loadTasksModel());
    }

    private static void createView(TasksModel model) {
        SwingTasksView view = new SwingTasksView(model);
        view.createSwingView();

        DisplayTrayIcon displayTrayIcon = new DisplayTrayIcon();
        displayTrayIcon.showIcon();

        IncomingTasksModel incomingTasksModel = new IncomingTasksModel(model);
        IncomingTasksView incomingTasksView = new IncomingTasksView(
                displayTrayIcon.getTrayIcon()
        );
        incomingTasksModel.addObserver(incomingTasksView);
        incomingTasksModel.runNotification();
    }
}
