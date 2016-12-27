package ua.shpota.tasks.model;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class TaskListTest extends AbstractTaskListTest {

    public TaskListTest(Class<? extends TaskList> tasksClass) {
        super(tasksClass);
    }

    // tests --------------------------------------------------------------

    @Test
    public void testSizeAddGet() {
        assertEquals(getTitle(), 0, tasks.size());
        Task[] ts = {Utils.task("A"), Utils.task("B"), Utils.task("C")};
        for (Task task : ts) 
            tasks.add(task);
        assertEquals(getTitle(), ts.length, tasks.size());
        Utils.assertContains(getTitle(), ts, tasks);
    }    
    
    @Test(expected = RuntimeException.class)
    public void testWrongAdd() {
        tasks.add(null);
    }
    
    @Test
    public void testRemove() {
        Task[] ts = {Utils.task("A"), Utils.task("B"), Utils.task("C"), Utils.task("D"), Utils.task("E")};
        for (Task task : ts) 
            tasks.add(task);
            
        // remove first
        tasks.remove(Utils.task("A"));
        Utils.assertContains(getTitle(), new Task[] { ts[1], ts[2], ts[3], ts[4] }, tasks);
        
        // remove last
        tasks.remove(Utils.task("E"));
        Utils.assertContains(getTitle(), new Task[] { ts[1], ts[2], ts[3] }, tasks);

        // remove middle
        tasks.remove(Utils.task("C"));
        Utils.assertContains(getTitle(), new Task[] { ts[1], ts[3] }, tasks);
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
        Utils.assertContains(getTitle(), ts, tasks);
    }
}












