package ua.shpota.tasks.view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class DisplayTrayIcon {
    private TrayIcon trayIcon;

    private static final SystemTray TRAY = SystemTray.getSystemTray();
    private static final String DESCRIPTION = "Tray icon";
    private static final String IMAGE_PATH = "/images/icon-1-16p.png";

    public TrayIcon getTrayIcon() {
        return trayIcon;
    }

    public void showIcon() {
        if (!SystemTray.isSupported()) {
            System.out.println("Error");
            System.exit(0);
            return;
        }

        PopupMenu popupMenu = new PopupMenu();
        trayIcon = new TrayIcon(createIcon(), "", popupMenu);
        trayIcon.setToolTip("Task manager");

        MenuItem item = new MenuItem("Exit");
        item.addActionListener(actionEvent -> System.exit(0));
        popupMenu.add(item);

        try {
            TRAY.add(trayIcon);
        } catch (AWTException e) {
            System.err.println("TrayIcon could not be added.");
            System.exit(0);
        }
    }

    private Image createIcon() {
        URL imageURL = this.getClass().getResource(IMAGE_PATH);
        return new ImageIcon(imageURL, DESCRIPTION).getImage();
    }
}
