package ua.shpota.tasks.model;

import java.util.Timer;

public class ScheduledTask {
    private TasksModel model;

    public ScheduledTask(TasksModel model) {
        this.model = model;
        getNotification();
    }

    private void getNotification() {
        NotificationTask notificationTask = new NotificationTask(model);
        Timer timer = new Timer();
        timer.schedule(notificationTask, 1000, 5 * 1000);
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException exception) {
            timer.cancel();
        }
    }
}
