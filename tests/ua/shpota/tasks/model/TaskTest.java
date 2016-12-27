package ua.shpota.tasks.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTest {

    @Test
    public void testTitle() {
        Task task = new Task("test", Utils.TODAY);
        assertEquals("test", task.getTitle());
        task.setTitle("other");
        assertEquals("other", task.getTitle());
    }

    @Test
    public void testActive() {
        Task task = new Task("test", Utils.TODAY);
        assertFalse(task.isActive());
        task.setActive(true);
        assertTrue(task.isActive());
    }

    @Test
    public void testConstructorNonrepeated() {
        Task task = new Task("test", Utils.TODAY);
        assertFalse("active", task.isActive());
        assertEquals("time", Utils.TODAY, task.getTime());
        assertEquals("start", Utils.TODAY, task.getStartTime());
        assertEquals("end", Utils.TODAY, task.getEndTime());
        assertFalse("repeated", task.isRepeated());
    }

    @Test
    public void testConstructorRepeated() {
        Task task = new Task("test", Utils.TODAY, Utils.TOMORROW, 5 * Utils.MINUTE);
        assertFalse("active", task.isActive());
        assertEquals("time", Utils.TODAY, task.getTime());
        assertEquals("start", Utils.TODAY, task.getStartTime());
        assertEquals("end", Utils.TOMORROW, task.getEndTime());
        assertTrue("repeated", task.isRepeated());
        assertEquals("repeatInterval", 5 * Utils.MINUTE, task.getRepeatInterval());
    }

    @Test
    public void testTimeNonRepeated() {
        Task task = new Task("test", Utils.TODAY, Utils.TOMORROW, 15 * Utils.MINUTE);
        task.setTime(Utils.TOMORROW);
        assertEquals("time", Utils.TOMORROW, task.getTime());
        assertEquals("start", Utils.TOMORROW, task.getStartTime());
        assertEquals("end", Utils.TOMORROW, task.getEndTime());
        assertFalse("repeated", task.isRepeated());
    }

    @Test
    public void testTimeRepeated() {
        Task task = new Task("test", Utils.TOMORROW);
        task.setTime(Utils.TODAY, Utils.TOMORROW, Utils.HOUR);
        assertEquals("time", Utils.TODAY, task.getTime());
        assertEquals("start", Utils.TODAY, task.getStartTime());
        assertEquals("end", Utils.TOMORROW, task.getEndTime());
        assertTrue("repeated", task.isRepeated());
        assertEquals("repeatInterval", Utils.HOUR, task.getRepeatInterval());
    }

    @Test
    public void testNextNonRepeative() {
        Task task = new Task("some", Utils.TODAY);
        task.setActive(true);
        assertEquals(Utils.TODAY, task.nextTimeAfter(Utils.YESTERDAY));
        assertEquals(Utils.TODAY, task.nextTimeAfter(Utils.ALMOST_TODAY));
        assertEquals(Utils.NEVER, task.nextTimeAfter(Utils.TODAY));
        assertEquals(Utils.NEVER, task.nextTimeAfter(Utils.TOMORROW));
    }

    @Test
    public void testNextRepeative() {
        Task task = new Task("some", Utils.TODAY, Utils.TOMORROW, Utils.HOUR);
        task.setActive(true);
        assertEquals(Utils.TODAY, task.nextTimeAfter(Utils.YESTERDAY));
        assertEquals(Utils.TODAY, task.nextTimeAfter(Utils.ALMOST_TODAY));
        assertEquals(Utils.TODAY_1H, task.nextTimeAfter(Utils.TODAY));
        assertEquals(Utils.TODAY_2H, task.nextTimeAfter(Utils.TODAY_1H));
        assertEquals(Utils.TODAY_3H, task.nextTimeAfter(Utils.TODAY_2H));
        assertEquals(Utils.TOMORROW, task.nextTimeAfter(Utils.ALMOST_TOMORROW));
        assertEquals(Utils.NEVER, task.nextTimeAfter(Utils.TOMORROW));
    }

    @Test
    public void testNextInactive() {
        Task task = new Task("some", Utils.TODAY);
        task.setActive(false);
        assertEquals(Utils.NEVER, task.nextTimeAfter(Utils.YESTERDAY));
    }

    @Test(expected = RuntimeException.class)
    public void testWrongTitle() {
        new Task(null, Utils.TODAY);
    }

    @Test(expected = RuntimeException.class)
    public void testWrongTitle2() {
        Task task = new Task("OK", Utils.TODAY);
        task.setTitle(null);
    }


    @Test(expected = RuntimeException.class)
    public void testWrongTime() {
        new Task("Title", null);
    }

    @Test(expected = RuntimeException.class)
    public void testWrongStart() {
        new Task("Title", null, Utils.TOMORROW, 5);
    }

    @Test(expected = RuntimeException.class)
    public void testWrongEnd() {
        new Task("Title", Utils.TOMORROW, Utils.TODAY, 3);
    }

    @Test(expected = RuntimeException.class)
    public void testWrongInterval() {
        new Task("Title", Utils.TODAY, Utils.TOMORROW, -1);
    }

    @Test(expected = RuntimeException.class)
    public void testWrongSetTime() {
        Task task = new Task("Title", Utils.TODAY);
        task.setTime(null);
    }

    @Test(expected = RuntimeException.class)
    public void testWrongSetStart() {
        Task task = new Task("Title", Utils.TODAY);
        task.setTime(null, Utils.TOMORROW, 3);
    }

    @Test(expected = RuntimeException.class)
    public void testWrongSetEnd() {
        Task task = new Task("Title", Utils.TODAY);
        task.setTime(Utils.TOMORROW, Utils.TODAY, 3);
    }

    @Test(expected = RuntimeException.class)
    public void testWrongSetInterval() {
        Task task = new Task("Title", Utils.TODAY);
        task.setTime(Utils.TODAY, Utils.TOMORROW, -1);
    }

    @Test(expected = RuntimeException.class)
    public void testWrongTimeAfter() {
        Task task = new Task("some", Utils.TODAY);
        task.nextTimeAfter(null);
    }
}
