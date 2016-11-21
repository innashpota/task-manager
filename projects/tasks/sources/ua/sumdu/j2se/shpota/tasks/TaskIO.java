package ua.sumdu.j2se.shpota.tasks;

import java.io.*;
import java.util.Date;

public class TaskIO {
    /*
     * Metod, shcho zapysuye zadachi iz spysku u potik u binarnomu formati
     */
    public static void write(TaskList tasks, OutputStream out) throws IOException {
        DataOutputStream dataOutput = new DataOutputStream(out);
        dataOutput.writeInt(tasks.size());

        for (Task task : tasks) {
            String titleTask = task.getTitle();
            dataOutput.writeInt(titleTask.length());
            dataOutput.writeUTF(titleTask);
            if (task.isActive()) {
                dataOutput.writeInt(1);
            } else {
                dataOutput.writeInt(0);
            }
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

    /*
     * Metod, shcho zchytuye zadachi iz potoku u danyy spysok zadach
     */
    public static void read(TaskList tasks, InputStream in) throws IOException {
        DataInputStream dataInput = new DataInputStream(in);
        int size = dataInput.readInt();
        for (int i = 0; i < size; i++) {
            int lengthTitle = dataInput.readInt();
            String titleTask = dataInput.readUTF();
            int activeInt = dataInput.readInt();
            Task task;
            int repeatInterval = dataInput.readInt();
            if (repeatInterval == 0) {
                Date time = new Date(dataInput.readLong());
                task = new Task(titleTask, time);
            } else {
                Date start = new Date(dataInput.readLong());
                Date end = new Date(dataInput.readLong());
                task = new Task(titleTask, start, end, repeatInterval);
            }
            boolean active = (activeInt != 0);
            task.setActive(active);
            tasks.add(task);
        }
    }

    /*
     * Metod, shcho zapysuye zadachi iz spysku u fayl
     */
    public static void writeBinary(TaskList tasks, File file) throws IOException {
        try (FileOutputStream fileOutput = new FileOutputStream(file)) {
            write(tasks, new ObjectOutputStream(fileOutput));
        }
    }

    /*
     * Metod, shcho zchytuye zadachi iz faylu u spysok zadach
     */
    public static void readBinary(TaskList tasks, File file) throws IOException {
        try (FileInputStream fileInput = new FileInputStream(file)) {
            read(tasks, new ObjectInputStream(fileInput));
        }
    }
}
