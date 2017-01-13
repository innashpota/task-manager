package ua.shpota.tasks.model;

import java.util.Date;
import java.util.TimerTask;

import static ua.shpota.tasks.model.Tasks.incoming;

public class NotificationTask extends TimerTask {
    private TasksModel model;

    public NotificationTask(TasksModel model) {
        this.model = model;
    }

    @Override
    public void run() {
        System.out.print(getStringBuilder().toString());
        //SwingScheduledView.showNotification(getStringBuilder().toString());
    }

    private StringBuilder getStringBuilder() {
        Date now = new Date();
        Date next = new Date(now.getTime() + 24 * 60 * 60 * 1000);
        Iterable<Task> incoming = incoming(model.getList(), now, next);

        StringBuilder sb = new StringBuilder();
        sb.append("Incoming tasks: \n");

        for (Task task : incoming) {
            sb.append(task.getTitle()).append("\n");
        }
        return sb;
    }
}
