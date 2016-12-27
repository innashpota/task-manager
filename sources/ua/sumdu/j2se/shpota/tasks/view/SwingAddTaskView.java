package ua.sumdu.j2se.shpota.tasks.view;

import ua.sumdu.j2se.shpota.tasks.model.TasksModel;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SwingConstants.LEFT;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Add tasks view.
 * Connected only with the model.
 * Implements {@link Observer}
 */
public class SwingAddTaskView implements Observer {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private TasksModel model;
    private JFrame frame;

    private JTextField title;
    private JFormattedTextField timeFormat;
    private JFormattedTextField startFormat;
    private JFormattedTextField endFormat;
    private JCheckBox active;
    private JCheckBox isRepeated;
    private JFormattedTextField intervalFormat;

    /**
     * Constructs add tasks view.
     *
     * @param model
     */
    public SwingAddTaskView(TasksModel model) {
        this.model = model;
        model.observable().addObserver(this);
    }

    /**
     * Creates add tasks view.
     */
    public void createAddTaskView() {
        createFrame();
        createFieldsBox();
        createButtonsPanel();
        showView();
    }

    private void createFrame() {
        frame = new JFrame("Add task");
        frame.setSize(new Dimension(400, 250));
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    private void createFieldsBox() {
        Box fieldsTaskBox = Box.createVerticalBox();

        Box titleBox = getTitleBox();
        Box checkBox = getCheckBox();

        timeFormat = createDataJFormat();
        timeFormat.setEditable(true);
        Box timeBox = getTimeBox(timeFormat, "    Time:", 18);

        startFormat = createDataJFormat();
        startFormat.setEditable(false);
        Box startBox = getTimeBox(startFormat, "    Start:", 18);

        endFormat = createDataJFormat();
        endFormat.setEditable(false);
        Box endBox = getTimeBox(endFormat, "    End:", 25);

        intervalFormat = createIntervalJFormat();
        intervalFormat.setEditable(false);
        Box intervalBox = getTimeBox(intervalFormat, "    Interval:", 4);

        isRepeated.addItemListener(itemEvent -> isRepeatedStateChanged());

        fieldsTaskBox.add(titleBox);
        fieldsTaskBox.add(checkBox);
        fieldsTaskBox.add(timeBox);
        fieldsTaskBox.add(startBox);
        fieldsTaskBox.add(endBox);
        fieldsTaskBox.add(intervalBox);
        frame.add(fieldsTaskBox);
    }

    private void isRepeatedStateChanged() {
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
    }

    private Box getTitleBox() {
        Box titleBox = Box.createHorizontalBox();
        titleBox.add(new JLabel("    Title:"));
        titleBox.add(Box.createHorizontalStrut(20));
        title = new JTextField(30);
        titleBox.add(title);

        return titleBox;
    }

    private Box getCheckBox() {
        Box checkBox = Box.createVerticalBox();
        isRepeated = new JCheckBox("Repeated:");
        isRepeated.setHorizontalTextPosition(LEFT);
        active = new JCheckBox("Active:");
        active.setHorizontalTextPosition(LEFT);
        checkBox.add(isRepeated);
        checkBox.add(active);

        return checkBox;
    }

    private static Box getTimeBox(JFormattedTextField format, String title, int width) {
        Box timeBox = Box.createHorizontalBox();
        timeBox.add(new JLabel(title));
        timeBox.add(Box.createHorizontalStrut(width));
        timeBox.add(format);

        return timeBox;
    }

    private JFormattedTextField createIntervalJFormat() {
        MaskFormatter formatter = null;
        intervalFormat = new JFormattedTextField(formatter);
        try {
            formatter = new MaskFormatter("#########");
        } catch (ParseException e) {
            showWarningMessage("Interval must not be empty");
        }
        formatter.setPlaceholderCharacter('_');
        formatter.setValidCharacters("0123456789");

        return intervalFormat;
    }

    private JFormattedTextField createDataJFormat() {
        JFormattedTextField jFormat = new JFormattedTextField(DATE_FORMAT);
        MaskFormatter dateMask = null;
        try {
            dateMask = new MaskFormatter("####-##-## ##:##");
            dateMask.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            showWarningMessage("Please follow the next pattern while entering date: yyyy-MM-dd HH:mm");
        }

        if (dateMask != null) {
            dateMask.install(jFormat);
        }
        jFormat.setToolTipText("Example: 2016-12-19 03:12");

        return jFormat;
    }

    private void createButtonsPanel() {
        JPanel buttonsPanel = new JPanel(new FlowLayout());

        JButton save = new JButton("Save");
        save.addActionListener(actionEvent -> {
            String titleStr = title.getText();
            if (titleStr.equals("")) {
                showWarningMessage("Title must not be empty");
                return;
            }

            if (isRepeated.isSelected()) {
                int interval = getInterval();
                Date start = getDate(startFormat);
                Date end = getDate(endFormat);

                if (interval != 0 && start != null && end != null) {
                    model.addTask(titleStr, start, end, interval, active.isSelected());
                }
            } else {
                Date time = getDate(timeFormat);
                if (time != null) {
                    model.addTask(titleStr, time, active.isSelected());
                }
            }
        });

        buttonsPanel.add(save);

        frame.add(buttonsPanel, SOUTH);
    }

    private Date getDate(JFormattedTextField format) {
        Date date = null;
        try {
            date = DATE_FORMAT.parse(format.getText());
        } catch (ParseException e) {
            showWarningMessage("Please follow the next pattern while entering date: yyyy-MM-dd HH:mm");
        }
        return date;
    }

    private int getInterval() {
        int interval = 0;
        String intervalStr = intervalFormat.getText();
        int indexSymbol = intervalStr.indexOf("_");
        char firstSymbol = intervalStr.charAt(0);
        if (indexSymbol != -1 && firstSymbol != '_') {
            interval = Integer.parseInt(intervalStr.substring(0, indexSymbol));
        } else if (firstSymbol != '_') {
            interval = Integer.parseInt(intervalStr);
        }

        if (interval == 0) {
            showWarningMessage("Interval must not be empty");
        }

        return interval;
    }

    private static void showWarningMessage(String message) {
        JOptionPane.showMessageDialog(null,
                message, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    private void showView() {
        frame.setVisible(true);
        frame.setResizable(false);
        frame.pack();
    }

    @Override
    public void update(Observable o, Object arg) {
        frame.setVisible(false);
    }
}
