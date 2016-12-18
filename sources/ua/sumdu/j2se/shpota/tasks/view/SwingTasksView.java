package ua.sumdu.j2se.shpota.tasks.view;

import ua.sumdu.j2se.shpota.tasks.model.TasksModel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class SwingTasksView implements Observer {
    private JFrame frame;
    private JTable table;
    private TasksModel model;

    public SwingTasksView(TasksModel model) {
        this.model = model;
        model.observable().addObserver(this);
    }

    public void createSwingView () {
        createFrame();
        createMenu();
        createLabel();
        createTable(model);
        createButtonsPanel();
        showView();
    }

    public void createFrame () {
        frame = new JFrame("TasksList");
        frame.setSize(new Dimension(600, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void createMenu () {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem removeItem = new JMenuItem("Calendar");
        JMenuItem addTaskItem = new JMenuItem("Add new task");
        menu.add(removeItem);
        removeItem.addActionListener(actionEvent -> {
            SwingCalendarView listTask = new SwingCalendarView();
            listTask.setVisible(true);
            listTask.pack();
        });
        menu.addSeparator();
        menu.add(addTaskItem);
        menuBar.add(menu);

        frame.setJMenuBar(menuBar);
    }

    public void createLabel () {
        JLabel countLabel = new JLabel("Tasks list", SwingConstants.CENTER);
        countLabel.setForeground(Color.BLUE);
        countLabel.setFont(new Font("Serif", Font.PLAIN, 18));

        frame.add(countLabel, BorderLayout.NORTH);
    }

    public void createTable (TasksModel model) {
        JTable table = new JTable(new TaskTable(model));
        this.table = table;
        JScrollPane tableScrollPane = new JScrollPane(table);

        frame.add(tableScrollPane);
    }

    public void createButtonsPanel() {
        JPanel buttonsPanel = new JPanel(new FlowLayout());

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(actionEvent -> {
            int selectedRow = table.getSelectedRow();
            model.remove(selectedRow);
        });

        JButton infoButton = new JButton("Information");

        buttonsPanel.add(removeButton);
        buttonsPanel.add(infoButton);
        frame.add(buttonsPanel, BorderLayout.SOUTH);
    }

    @Override
    public void update(Observable o, Object arg) {
        table.updateUI();
    }

    public void showView () {
        frame.setVisible(true);
        frame.pack();
    }

    public void close () {
        frame.setVisible(false);
        frame.dispose();
    }
}
