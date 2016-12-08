package ua.sumdu.j2se.shpota.tasks.view;

import ua.sumdu.j2se.shpota.tasks.model.LinkedTaskList;
import ua.sumdu.j2se.shpota.tasks.model.TaskIO;
import ua.sumdu.j2se.shpota.tasks.model.TaskModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class SwingTasksListView extends JFrame {
    private JButton calendar;
    private JButton addNewTask;
    private JLabel countLabel;

    public SwingTasksListView() throws IOException, ParseException {
        super("TasksList");

        countLabel = new JLabel("Tasks list", SwingConstants.CENTER);
        countLabel.setForeground(Color.BLUE);
        countLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        add(countLabel, BorderLayout.NORTH);

        LinkedTaskList list = new LinkedTaskList();
        TaskIO.readText(list, new File("write.txt"));

        TableModel model = new TaskModel(list);
        JTable table = new JTable(model);
        getContentPane().add(new JScrollPane(table));
        setPreferredSize(new Dimension(400, 220));
        setLocationRelativeTo(null);

        calendar = new JButton("Calendar");
        calendar.addActionListener(ActionListener -> {
            SwingCalendarView listTask = new SwingCalendarView();
            listTask.setVisible(true);
            listTask.pack();
        });
        addNewTask = new JButton("Add new task");

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(calendar);
        buttonsPanel.add(addNewTask);
        add(buttonsPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
