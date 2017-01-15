package ua.shpota.tasks.model;

import ua.shpota.tasks.view.SwingScheduledView;

import java.util.Timer;

public class ScheduledTask {
    private TasksModel model;

    public ScheduledTask(TasksModel model) {
        this.model = model;
        runNotification();
    }

    private void runNotification() {
        SwingScheduledView scheduledView = new SwingScheduledView(model);
        Timer timer = new Timer();
        timer.schedule(scheduledView, 1000, 5 * 1000);
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException exception) {
            timer.cancel();
        }
    }
}
