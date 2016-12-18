package ua.sumdu.j2se.shpota.tasks.controller;

import ua.sumdu.j2se.shpota.tasks.model.TasksModel;
import ua.sumdu.j2se.shpota.tasks.model.TaskIO;
import ua.sumdu.j2se.shpota.tasks.view.SwingTasksView;

import java.io.IOException;

public class TaskController {
    private TasksModel model;

    public static void main (String[] args) throws IOException {
        TasksModel model = TaskIO.loadTaskModel();
        TaskController controller = new TaskController(model);
        controller.createView();
    }

    public TaskController (TasksModel model) {
        this.model = model;
    }

    public void createView () {
        SwingTasksView view = new SwingTasksView(model);
        view.createSwingView();
    }
}
