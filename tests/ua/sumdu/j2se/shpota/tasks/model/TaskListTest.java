package ua.sumdu.j2se.shpota.tasks.model;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static ua.sumdu.j2se.shpota.tasks.model.Utils.assertContains;
import static ua.sumdu.j2se.shpota.tasks.model.Utils.task;

public class TaskListTest extends AbstractTaskListTest {

    public TaskListTest(Class<? extends TaskList> tasksClass) {
        super(tasksClass);
    }

    // tests --------------------------------------------------------------

    @Test
    public void testSizeAddGet() {
        assertEquals(getTitle(), 0, tasks.size());
        Task[] ts = {task("A"), task("B"), task("C")};
        for (Task task : ts) 
            tasks.add(task);
        assertEquals(getTitle(), ts.length, tasks.size());
        assertContains(getTitle(), ts, tasks);
    }    
    
    @Test(expected = RuntimeException.class)
    public void testWrongAdd() {
        tasks.add(null);
    }
    
    @Test
    public void testRemove() {
        Task[] ts = {task("A"),task("B"),task("C"),task("D"),task("E")};
        for (Task task : ts) 
            tasks.add(task);
            
        // remove first
        tasks.remove(task("A"));
        assertContains(getTitle(), new Task[] { ts[1], ts[2], ts[3], ts[4] }, tasks);
        
        // remove last
        tasks.remove(task("E"));
        assertContains(getTitle(), new Task[] { ts[1], ts[2], ts[3] }, tasks);

        // remove middle
        tasks.remove(task("C"));
        assertContains(getTitle(), new Task[] { ts[1], ts[3] }, tasks);
    }
    
    @Test(expected = RuntimeException.class)
    public void testInvalidRemove() {
        tasks.remove(null);
    }
    
    @Test
    public void testAddManyTasks() {
        Task[] ts = new Task[1000];
        for (int i = 0; i < ts.length; i++) {
            ts[i] = new Task("Task#"+ i, new Date(i));
            tasks.add(ts[i]);
        }
        assertContains(getTitle(), ts, tasks);
    }
}












