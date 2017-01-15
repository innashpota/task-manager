package ua.shpota.tasks.view;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class IncomingTasksView implements Observer {
    private TrayIcon trayIcon;

    public IncomingTasksView(TrayIcon trayIcon) {
        this.trayIcon = trayIcon;
    }

    @Override
    public void update(Observable o, Object message) {
        trayIcon.displayMessage(
                "Incoming tasks:",
                String.valueOf(message),
                TrayIcon.MessageType.INFO
        );
    }
}