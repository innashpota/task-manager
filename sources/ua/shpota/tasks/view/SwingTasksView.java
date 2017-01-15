package ua.shpota.tasks.view;

import ua.shpota.tasks.model.TasksModel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import static java.awt.Color.BLUE;
import static java.awt.Font.PLAIN;
import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SwingConstants.CENTER;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 * Tasks view.
 * Connected only with the model.
 * Implements {@link Observer}
 */
public class SwingTasksView implements Observer {
    private TasksModel model;
    private JFrame frame;
    private JTable table;

    /**
     * Constructs tasks view
     *
     * @param model
     */
    public SwingTasksView(TasksModel model) {
        this.model = model;
        model.observable().addObserver(this);
    }

    /**
     * Creates tasks view
     */
    public void createSwingView() {
        createFrame();
        createMenu();
        createLabel();
        createTable(model);
        createButtonsPanel();
        showView();
    }

    private void createFrame() {
        frame = new JFrame("Task manager");
        frame.setIconImage(Toolkit.getDefaultToolkit().
                getImage(getClass().getResource("/images/icon-1.png")));
        frame.setSize(new Dimension(300, 400));
        frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new DialogWindowAdapter(model));
        frame.setLocationRelativeTo(null);
    }

    private void createMenu() {
        /*JMenuBar menuBar = new JMenuBar();
        JMenu calendar = new JMenu("Calendar");
        JMenuItem calendarItem = new JMenuItem("Calendar");
        calendar.add(calendarItem);
        menuBar.add(calendar);

        frame.setJMenuBar(menuBar);*/
    }

    private void createLabel() {
        JLabel countLabel = new JLabel("All tasks", CENTER);
        countLabel.setForeground(BLUE);
        countLabel.setFont(new Font("Serif", PLAIN, 18));

        frame.add(countLabel, NORTH);
    }

    private void createTable(TasksModel model) {
        JTable table = new JTable(new TaskTable(model));
        this.table = table;
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        frame.add(tableScrollPane);
    }

    private void createButtonsPanel() {
        JPanel buttonsPanel = new JPanel(new FlowLayout());

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(actionEvent -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                model.remove(selectedRow);
            }
        });

        JButton addNewTaskButton = new JButton("Add task");
        addNewTaskButton.addActionListener(actionEvent -> {
            SwingAddTaskView addTaskView = new SwingAddTaskView(model);
            addTaskView.createAddTaskView();
        });

        //JButton infoButton = new JButton("Information");

        buttonsPanel.add(removeButton);
        buttonsPanel.add(addNewTaskButton);
        //buttonsPanel.add(infoButton);

        frame.add(buttonsPanel, SOUTH);
    }

    private void showView() {
        frame.setVisible(true);
        frame.pack();
    }

    @Override
    public void update(Observable o, Object arg) {
        table.updateUI();
    }
}
