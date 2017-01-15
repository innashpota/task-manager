package ua.shpota.tasks.view;

import ua.shpota.tasks.model.NotificationTask;
import ua.shpota.tasks.model.TasksModel;

import java.awt.*;
import java.util.TimerTask;

public class SwingScheduledView extends TimerTask{
    private TasksModel model;

    public SwingScheduledView(TasksModel model) {
        this.model = model;
    }

    @Override
    public void run() {
        NotificationTask notificationTask = new NotificationTask(model);
        DisplayTrayIcon.trayIcon.displayMessage("Incoming tasks:",
                notificationTask.getNotification(), TrayIcon.MessageType.INFO);
    }
}
