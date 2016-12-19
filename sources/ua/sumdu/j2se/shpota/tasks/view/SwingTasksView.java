package ua.sumdu.j2se.shpota.tasks.view;

import ua.sumdu.j2se.shpota.tasks.model.TasksModel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;

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
        frame = new JFrame("Task manager");
        frame.setSize(new Dimension(400, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void createMenu () {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Calendar");
        JMenuItem removeItem = new JMenuItem("Calendar");
        menu.add(removeItem);
        removeItem.addActionListener(actionEvent -> {
            SwingCalendarView listTask = new SwingCalendarView();
            listTask.setVisible(true);
            listTask.pack();
        });
        menuBar.add(menu);

        frame.setJMenuBar(menuBar);
    }

    public void createLabel () {
        JLabel countLabel = new JLabel("All tasks", SwingConstants.CENTER);
        countLabel.setForeground(Color.BLUE);
        countLabel.setFont(new Font("Serif", Font.PLAIN, 18));

        frame.add(countLabel, NORTH);
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
            if (selectedRow != -1) {
                model.remove(selectedRow);
            }
        });

        JButton infoButton = new JButton("Information");
        JButton addNewTaskButton = new JButton("Add task");

        buttonsPanel.add(removeButton);
        buttonsPanel.add(infoButton);
        buttonsPanel.add(addNewTaskButton);
        frame.add(buttonsPanel, SOUTH);
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
