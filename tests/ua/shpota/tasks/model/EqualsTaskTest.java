package ua.shpota.tasks.model;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class EqualsTaskTest {

    public static final Date NOW = new Date();
    
    @Test
    public void testEqualsToItself() {
        Task task1 = new Task("Some", NOW);
        assertEquals("Task must be equals to itself", task1, task1);
    }

    @Test
    public void testEquals() {
        Task task1 = new Task("Some", NOW);
        Task task2 = new Task("Some", NOW);
        assertEquals("Tasks, created with same constructor must be equals", task1, task2);
        assertEquals("a = b <=> b = a", task2, task1);
    }

    @Test
    public void testEqualsActivity() {
        Task task1 = new Task("Some", NOW);
        Task task2 = new Task("Some", NOW);
        task2.setActive(true);
        assertFalse("Active task couldn't equal to inactive", task1.equals(task2));
    }

    @Test
    public void testEqualsChanged() {
        Task task1 = new Task("Some", NOW);
        Task task2 = new Task("Some", new Date(NOW.getTime() + Utils.MONTH));
        task1.setTime(NOW, new Date(NOW.getTime() + Utils.DAY), Utils.HOUR);
        task2.setTime(NOW, new Date(NOW.getTime() + Utils.DAY), Utils.HOUR);
        assertEquals("Tasks with same state must equal", task1, task2);
    }

    @Test
    public void testEqualsNull() {
        Task task1 = new Task("Some", NOW);
        assertFalse("any.equals(null) must be false", task1.equals(null));
    }

    @Test
    public void testEqualsToString() {
        Task task1 = new Task("Some", NOW);
        assertFalse("Task can be equal only to task", task1.equals("some string"));
    }

    @Test
    public void testEqualsSymmetry() {
        Task egoist = new EgoistTask("Title", NOW);
        Task simple = new Task("Title", NOW);
        assertEquals("equals must be simmetric", egoist.equals(simple), simple.equals(egoist));
    }

    @Test
    public void testHashCode() {
        Task task1 = new Task("Some", NOW);
        Task task2 = new Task("Some", new Date(NOW.getTime() + Utils.MONTH));
        task1.setTime(NOW, new Date(NOW.getTime() + Utils.DAY), Utils.HOUR);
        task2.setTime(NOW, new Date(NOW.getTime() + Utils.DAY), Utils.HOUR);
        task1.setActive(true);
        task2.setActive(true);
        
        assertEquals(task1.hashCode(), task2.hashCode());
    }
}

class EgoistTask extends Task {
    public EgoistTask(String title, Date time) {
        super(title, time);
    }
    public boolean equals(Object obj) {
        return false;
    }
}












