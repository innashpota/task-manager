package ua.sumdu.j2se.shpota.tasks;

import org.junit.Test;

import java.util.Collections;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static ua.sumdu.j2se.shpota.tasks.Utils.task;

public class EqualsListTest extends AbstractTaskListTest {

    public EqualsListTest(Class<? extends TaskList> tasksClass) {
        super(tasksClass);
    }

    @Test
    public void testEquals() throws Exception {
        Task[] elements = {task("A"), task("B"), task("C")};

        TaskList list1 = createList();
        TaskList list2 = createList();
        for (Task task : elements) {
            list1.add(task);
            list2.add(task);
        }
        assertEquals(getTitle(), list1, list2);
        assertEquals(getTitle(), list2, list1);
    }

    @Test
    public void testEqualsNull() {
        assertFalse(getTitle(), tasks.equals(null));
    }

    @Test
    public void testIdentityEquals() {
        assertEquals(getTitle(), tasks, tasks);
    }

    @Test
    public void testSymmetricEquals() {
        TaskList dummy = new DummyList();
        assertEquals(getTitle(), tasks.equals(dummy), dummy.equals(tasks));
    }

    @Test
    public void testHashCode() throws Exception {
        Task[] elements = {task("A"), task("B"), task("C")};

        TaskList list1 = createList();
        TaskList list2 = createList();
        for (Task task : elements) {
            list1.add(task);
            list2.add(task);
        }

        assertEquals(getTitle(), list1.hashCode(), list2.hashCode());
    }
}

class DummyList extends TaskList {

    public void add(Task task) {
    }

    public boolean remove(Task task) {
        return true;
    }

    public int size() {
        return 0;
    }

    public Task getTask(int index) {
        throw new IndexOutOfBoundsException();
    }

    public Iterator<Task> iterator() {
        return Collections.<Task>emptyList().iterator();
    }

    public boolean equals(Object obj) {
        return false;
    }
}












