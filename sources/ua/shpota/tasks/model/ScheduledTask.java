package ua.shpota.tasks.model;

import java.util.Date;
import java.util.TimerTask;

import static ua.shpota.tasks.model.Tasks.incoming;

public class ScheduledTask extends TimerTask {
    private TasksModel model;

    public ScheduledTask(TasksModel model) {
        this.model = model;
    }

    @Override
    public void run() {
        Date now = new Date();
        Date next = new Date(now.getTime() + 24 * 60 * 60 * 1000);
        Iterable<Task> incoming = incoming(model.getList(), now, next);

        StringBuilder sb = new StringBuilder();
        sb.append("Incoming tasks: \n");

        for (Task task : incoming) {
            sb.append(task.getTitle()).append("\n");
        }

        System.out.print(sb.toString());
    }
}
