package ua.shpota.tasks.view;

import ua.shpota.tasks.model.ScheduledTask;
import ua.shpota.tasks.model.TasksModel;

import java.util.Timer;

public class SwingScheduledView {
    private TasksModel model;

    public SwingScheduledView(TasksModel model) {
        this.model = model;
        showNotification();
    }

    private void showNotification() {
        ScheduledTask notificationTask = new ScheduledTask(model);
        Timer timer = new Timer();

        timer.schedule(notificationTask, 500, 5000);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException exception) {
            timer.cancel();
        }
    }
}
