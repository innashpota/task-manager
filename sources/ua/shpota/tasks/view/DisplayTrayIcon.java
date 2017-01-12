package ua.shpota.tasks.view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class DisplayTrayIcon {
    public static TrayIcon trayIcon;

    private final SystemTray tray = SystemTray.getSystemTray();

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
        trayIcon = new TrayIcon(createIcon("/images/icon-1-16p.png", "Tray icon"), "", popupMenu);
        trayIcon.setToolTip("Task manager");
        trayIcon.addActionListener(ActionEvent -> trayIcon.displayMessage("Incoming tasks:",
                "Some tasks title", TrayIcon.MessageType.INFO));

        MenuItem item = new MenuItem("Exit");
        item.addActionListener(ActionEvent -> System.exit(0));
        popupMenu.add(item);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.err.println("TrayIcon could not be added.");
        }
    }

    private Image createIcon(String path, String desc) {
        URL imageURL = SwingScheduledView.class.getResource(path);
        return new ImageIcon(imageURL, desc).getImage();
    }
}
