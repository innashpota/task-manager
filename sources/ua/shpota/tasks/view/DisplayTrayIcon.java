package ua.shpota.tasks.view;

import ua.shpota.tasks.model.ScheduledTask;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class DisplayTrayIcon {
    public static TrayIcon trayIcon;

    private final SystemTray tray = SystemTray.getSystemTray();
    private final String desc = "Tray icon";
    private final String path = "/images/icon-1-16p.png";

    public DisplayTrayIcon() {
        showIcon();
    }

    private void showIcon() {
        if (!SystemTray.isSupported()) {
            System.out.println("Error");
            System.exit(0);
            return;
        }

        PopupMenu popupMenu = new PopupMenu();
        trayIcon = new TrayIcon(createIcon(), "", popupMenu);
        trayIcon.setToolTip("Task manager");
        /*trayIcon.addActionListener(ActionEvent -> trayIcon.displayMessage("Incoming tasks:",
                message, TrayIcon.MessageType.INFO));*/
        trayIcon.displayMessage("Incoming tasks:",
                "message" , TrayIcon.MessageType.INFO);
        MenuItem item = new MenuItem("Exit");
        item.addActionListener(actionEvent -> System.exit(0));
        popupMenu.add(item);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.err.println("TrayIcon could not be added.");
        }
    }

    private Image createIcon() {
        URL imageURL = ScheduledTask.class.getResource(path);
        return new ImageIcon(imageURL, desc).getImage();
    }
}
