package ua.sumdu.j2se.shpota.tasks.view;

import ua.sumdu.j2se.shpota.tasks.model.Task;
import ua.sumdu.j2se.shpota.tasks.model.TasksModel;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SwingConstants.LEFT;

public class SwingAddTaskView implements Observer {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private TasksModel model;
    private JFrame frame;

    private Task task;
    private JTextField title;
    private JFormattedTextField timeFormat;
    private JFormattedTextField startFormat;
    private JFormattedTextField endFormat;
    private JCheckBox active;
    private JCheckBox isRepeated;
    private JFormattedTextField intervalFormat;

    public SwingAddTaskView(TasksModel model) {
        this.model = model;
        model.observable().addObserver(this);
    }

    public void createAddTaskView() {
        createFrame();
        createFieldsBox();
        createButtonsPanel();
        showView();
    }

    private void createFrame() {
        frame = new JFrame("Add task");
        frame.setSize(new Dimension(400, 500));
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    private void createFieldsBox() {
        Box fieldsTaskBox = Box.createVerticalBox();

        Box titleBox = Box.createHorizontalBox();
        titleBox.add(new JLabel("Title:"));
        titleBox.add(Box.createHorizontalStrut(19));
        title = new JTextField(30);
        titleBox.add(title);

        Box checkBox = Box.createVerticalBox();
        isRepeated = new JCheckBox("Repeated:");
        isRepeated.setHorizontalTextPosition(LEFT);

        active = new JCheckBox("Active:");
        active.setHorizontalTextPosition(LEFT);
        checkBox.add(isRepeated);
        checkBox.add(active);

        Box timeBox = Box.createHorizontalBox();
        timeBox.add(new JLabel("Time:"));
        timeBox.add(Box.createHorizontalStrut(18));
        timeFormat = createDataJFormat();
        timeBox.add(timeFormat);

        Box startBox = Box.createHorizontalBox();
        startBox.add(new JLabel("Start:"));
        startBox.add(Box.createHorizontalStrut(18));
        startFormat = createDataJFormat();
        startFormat.setEditable(false);
        startBox.add(startFormat);

        Box endBox = Box.createHorizontalBox();
        endBox.add(new JLabel("End:"));
        endBox.add(Box.createHorizontalStrut(13 + 12));
        endFormat = createDataJFormat();
        endFormat.setEditable(false);
        endBox.add(endFormat);

        Box intervalBox = Box.createHorizontalBox();
        intervalBox.add(new JLabel("Interval:"));
        intervalBox.add(Box.createHorizontalStrut(4));
        intervalFormat = createIntervalJFormat();
        intervalFormat.setEditable(false);
        intervalBox.add(intervalFormat);

        isRepeated.addItemListener(itemEvent -> {
            if (isRepeated.isSelected()) {
                timeFormat.setEditable(false);
                startFormat.setEditable(true);
                endFormat.setEditable(true);
                intervalFormat.setEditable(true);
            } else {
                timeFormat.setEditable(true);
                startFormat.setEditable(false);
                endFormat.setEditable(false);
                intervalFormat.setEditable(false);
            }
        });

        fieldsTaskBox.add(titleBox);
        fieldsTaskBox.add(checkBox);
        fieldsTaskBox.add(timeBox);
        fieldsTaskBox.add(startBox);
        fieldsTaskBox.add(endBox);
        fieldsTaskBox.add(intervalBox);
        frame.add(fieldsTaskBox);
    }

    private JFormattedTextField createIntervalJFormat() {
        intervalFormat = new JFormattedTextField();
        intervalFormat.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });

        return intervalFormat;
    }

    private JFormattedTextField createDataJFormat() {
        JFormattedTextField jFormat = new JFormattedTextField(DATE_FORMAT);
        MaskFormatter dateMask = null;
        try {
            dateMask = new MaskFormatter("####-##-## ##:##");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (dateMask != null) {
            dateMask.install(jFormat);
        }

        return jFormat;
    }

    private void createButtonsPanel() {
        JPanel buttonsPanel = new JPanel(new FlowLayout());

        JButton save = new JButton("Save");
        save.addActionListener(actionEvent -> {
            String titleStr = title.getText();
            Date time;
            Date start;
            Date end;
            int interval = Integer.parseInt(intervalFormat.getText());

            if (isRepeated.isSelected()) {
                try {
                    start = DATE_FORMAT.parse(startFormat.getText());
                    end = DATE_FORMAT.parse(endFormat.getText());
                    task = new Task(titleStr, start, end, interval);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    time = DATE_FORMAT.parse(timeFormat.getText());
                    task = new Task(titleStr, time);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            model.add(task);
        });

        buttonsPanel.add(save);

        frame.add(buttonsPanel, SOUTH);
    }

    private void showView() {
        frame.setVisible(true);
        frame.pack();
    }

    public void close() {
        frame.setVisible(false);
        frame.dispose();
    }

    @Override
    public void update(Observable o, Object arg) {
        title.setText("");
        timeFormat.setValue(null);
        startFormat.setValue(null);
        endFormat.setValue(null);
        active.setSelected(false);
        isRepeated.setSelected(false);
        intervalFormat.setText("");
    }
}
