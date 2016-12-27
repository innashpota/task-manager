package ua.sumdu.j2se.shpota.tasks.model;

import java.io.*;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class TaskIO {
    private static final SimpleDateFormat DATE_FORMAT =
            new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss.S]");
    private static final String FILE_NAME = "./task-storage.txt";

    /**
     * Loads list of task from text file
     *
     * @return list
     * @throws IOException
     */
    public static TaskList loadFile() throws IOException {
        File file = getFile();
        TaskList list = new ArrayTaskList();
        readText(list, file);
        return list;
    }

    /**
     * Stores list of task into text file
     *
     * @param list
     * @throws IOException
     */
    public static void storeFile(TaskList list) throws IOException {
        File file = getFile();
        writeText(list, file);
    }

    private static File getFile() throws IOException {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    /**
     * Writes tasks into the stream in binary format.
     *
     * @param tasks
     * @param out
     * @throws IOException
     */
    public static void write(TaskList tasks, OutputStream out) throws IOException {
        DataOutputStream dataOutput = new DataOutputStream(out);
        dataOutput.writeInt(tasks.size());

        for (Task task : tasks) {
            String titleTask = task.getTitle();
            dataOutput.writeInt(titleTask.length());
            dataOutput.writeUTF(titleTask);
            dataOutput.writeInt(task.isActive() ? 1 : 0);
            dataOutput.writeInt(task.getRepeatInterval());
            if (task.isRepeated()) {
                dataOutput.writeLong(task.getStartTime().getTime());
                dataOutput.writeLong(task.getEndTime().getTime());
            } else {
                dataOutput.writeLong(task.getTime().getTime());
            }
        }
        dataOutput.flush();
    }

    /**
     * Reads tasks from the stream in binary format.
     *
     * @param tasks
     * @param in
     * @throws IOException
     */
    public static void read(TaskList tasks, InputStream in) throws IOException {
        DataInputStream dataInput = new DataInputStream(in);
        int size = dataInput.readInt();
        for (int i = 0; i < size; i++) {
            int lengthTitle = dataInput.readInt();
            String titleTask = dataInput.readUTF();
            int active = dataInput.readInt();
            Task task = null;
            int repeatInterval = dataInput.readInt();
            if (repeatInterval == 0) {
                Date time = new Date(dataInput.readLong());
                task = new Task(titleTask, time);
            } else {
                Date start = new Date(dataInput.readLong());
                Date end = new Date(dataInput.readLong());
                task = new Task(titleTask, start, end, repeatInterval);
            }
            task.setActive(active != 0);
            tasks.add(task);
        }
    }

    /**
     * Writes tasks into the file in binary format.
     *
     * @param tasks
     * @param file
     * @throws IOException
     */
    public static void writeBinary(TaskList tasks, File file) throws IOException {
        try (FileOutputStream fileOutput = new FileOutputStream(file)) {
            write(tasks, new ObjectOutputStream(fileOutput));
        }
    }

    /**
     * Reads tasks from the file in binary format.
     *
     * @param tasks
     * @param file
     * @throws IOException
     */
    public static void readBinary(TaskList tasks, File file) throws IOException {
        try (FileInputStream fileInput = new FileInputStream(file)) {
            read(tasks, new ObjectInputStream(fileInput));
        }
    }

    /**
     * Writes tasks into the stream in text format.
     *
     * @param tasks
     * @param out
     * @throws IOException
     */
    public static void write(TaskList tasks, Writer out) throws IOException {
        PrintWriter writer = new PrintWriter(out);
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            writer.write("\"" + task.getTitle().replaceAll("\"", "\"\"") + "\"");
            boolean repeated = task.isRepeated();
            writer.write(repeated ? " from " : " at ");
            if (repeated) {
                writer.write(DATE_FORMAT.format(task.getStartTime()));
                writer.write(" to ");
                writer.write(DATE_FORMAT.format(task.getEndTime()));
                writer.write(" every ");
                writer.write("[" + task.getRepeatInterval() + "]");
            } else {
                writer.write(DATE_FORMAT.format(task.getTime()));
            }
            writer.write(task.isActive() ? " active" : " inactive");
            writer.write(iterator.hasNext() ? ";" : ".");
            writer.println();
        }
        writer.flush();
    }

    /**
     * Reads tasks from the stream in text format.
     *
     * @param tasks
     * @param in
     * @throws IOException
     */
    public static void read(TaskList tasks, Reader in) throws IOException {
        BufferedReader reader = new BufferedReader(in);
        String str = reader.ready() ? reader.readLine() : null;
        boolean endStr = false;
        while (str != null && !endStr) {
            int lastSymbol = str.lastIndexOf("\"");
            String title = str.substring(1, lastSymbol).replaceAll("\"\"", "\"");
            String atOrFrom = String.valueOf(str.charAt(lastSymbol + 2));

            Task task = null;
            int firstBracket = str.indexOf("[", lastSymbol);
            int endBracket = str.lastIndexOf("]");
            Date time = DATE_FORMAT.parse(str, new ParsePosition(firstBracket));

            if (atOrFrom.equals("a")) {
                task = new Task(title, time);
            } else {
                int secondBracket = str.indexOf("[", firstBracket + 1);
                Date end = DATE_FORMAT.parse(str, new ParsePosition(secondBracket));
                int startInterval = str.indexOf("[", secondBracket + 1);
                String intervalStr = str.substring(startInterval + 1, endBracket);
                int interval = Integer.valueOf(intervalStr);
                task = new Task(title, time, end, interval);
            }

            String inactive = str.substring(endBracket + 2, str.length() - 1);
            task.setActive(!inactive.equals("inactive"));
            tasks.add(task);
            if (str.endsWith(";")) {
                str = reader.readLine();
            } else {
                endStr = true;
            }
        }
    }

    /**
     * Writes tasks into the file in text format.
     *
     * @param tasks
     * @param file
     * @throws IOException
     */
    public static void writeText(TaskList tasks, File file) throws IOException {
        try (PrintWriter fileWriter = new PrintWriter(file)) {
            write(tasks, new PrintWriter(fileWriter));
        }
    }

    /**
     * Reads tasks from the file in text format.
     *
     * @param tasks
     * @param file
     * @throws IOException
     */
    public static void readText(TaskList tasks, File file) throws IOException {
        try (FileReader fileReader = new FileReader(file)) {
            read(tasks, new BufferedReader(fileReader));
        }
    }
}
