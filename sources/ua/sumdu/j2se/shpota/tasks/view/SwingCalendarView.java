package ua.sumdu.j2se.shpota.tasks.view;

import javax.swing.*;
import java.awt.*;

public class SwingCalendarView extends JFrame {
    private JButton addNewTask;
    private JLabel countLabel;

    public SwingCalendarView() {
        super("Calendar");
        countLabel = new JLabel("Calendar", SwingConstants.CENTER);
        countLabel.setForeground(Color.BLUE);
        countLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        addNewTask = new JButton("Add new task");

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        add(countLabel, BorderLayout.NORTH);

        buttonsPanel.add(addNewTask);

        add(buttonsPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
