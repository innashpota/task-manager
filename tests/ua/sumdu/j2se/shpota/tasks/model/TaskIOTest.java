package ua.sumdu.j2se.shpota.tasks.model;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static ua.sumdu.j2se.shpota.tasks.model.Utils.*;

public class TaskIOTest
{
    @Test
    public void testBinary() throws Exception
    {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        
        TaskList tasks = new ArrayTaskList(),
                 tasks2 = new ArrayTaskList();
        tasks.add(new Task("A", TODAY));
        tasks.add(new Task("B", TOMORROW));
        tasks.add(new Task("C", TODAY, TOMORROW, HOUR));
        tasks.add(new Task("D", YESTERDAY));
        
        TaskIO.write(tasks, out);
        
        TaskIO.read(tasks2, in);
        
        assertEquals(tasks, tasks2);
    }
    
    @Test(expected = Exception.class)
    public void testBinError() throws Exception
    {
        TaskIO.read(new ArrayTaskList(), new ByteArrayInputStream(new byte[0]));
    }
    
    @Test
    public void testText() throws Exception
    {
        PipedReader in = new PipedReader();
        PipedWriter out = new PipedWriter(in);

        TaskList tasks = new ArrayTaskList(),
                tasks2 = new ArrayTaskList();

        tasks.add(new Task("A", TODAY));
        Task t = new Task("B", TOMORROW);
        t.setActive(true);
        tasks.add(t);
        tasks.add(new Task("C", TODAY, TOMORROW, HOUR));
        tasks.add(new Task("D", YESTERDAY));

        TaskIO.write(tasks, out);

        TaskIO.read(tasks2, in);

        assertEquals(tasks, tasks2);
    }

    @Test
    public void testTextEmptyList() throws Exception
    {
        PipedReader in = new PipedReader();
        PipedWriter out = new PipedWriter(in);

        TaskList tasks = new ArrayTaskList(),
                tasks2 = new ArrayTaskList();

        TaskIO.write(tasks, out);

        TaskIO.read(tasks2, in);

        assertEquals(tasks, tasks2);
    }

    @Test(expected = Exception.class)
    public void testTextError() throws Exception
    {
        TaskIO.read(new ArrayTaskList(), new StringReader("some"));
    }
    
    @Test
    public void testSerialArray() throws Exception
    {
        ObjectOutputStream out = new ObjectOutputStream(new ByteArrayOutputStream());

        TaskList tasks = new ArrayTaskList();
        tasks.add(new Task("A", TODAY));
        out.writeObject(tasks);
        out.close();
    }

    @Test
    public void testSerialList() throws Exception
    {
        ObjectOutputStream out = new ObjectOutputStream(new ByteArrayOutputStream());

        TaskList tasks = new LinkedTaskList();
        tasks.add(new Task("A", TODAY));
        out.writeObject(tasks);
        out.close();
    }
}










