package ua.shpota.tasks.model;

import java.util.Date;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import static ua.shpota.tasks.model.Tasks.incoming;

public class IncomingTasksModel extends Observable {
    private TasksModel model;

    private static final int INTERVAL = 60 * 1000;

    public IncomingTasksModel(TasksModel model) {
        this.model = model;
    }

    public void runNotification() {
        Scheduled scheduled = new Scheduled();
        Timer timer = new Timer();
        timer.schedule(scheduled, 1000, INTERVAL);
    }

    private class Scheduled extends TimerTask {

        @Override
        public void run() {
            NotificationTask notificationTask = new NotificationTask(model);
            if (!notificationTask.getNotification().equals("")) {
                IncomingTasksModel.super.setChanged();
                IncomingTasksModel.super.notifyObservers(
                        notificationTask.getNotification()
                );
            }
        }
    }

    private class NotificationTask {
        private TasksModel model;

        NotificationTask(TasksModel model) {
            this.model = model;
        }

        String getNotification() {
            Date now = new Date();
            Date next = new Date(now.getTime() + INTERVAL);
            Iterable<Task> incoming = incoming(model.getList(), now, next);

            StringBuilder sb = new StringBuilder();

            for (Task task : incoming) {
                sb.append(task.getTitle()).append("\n");
            }

            return sb.toString();
        }
    }
}
