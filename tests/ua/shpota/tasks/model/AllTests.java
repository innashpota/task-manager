package ua.shpota.tasks.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/* --------------------------------------------------- *
 * All tests in one suite.                             *
 * Run only this test class.                           *
 * You can run only some of them,                      *
 * just comment the tests you don't want to run.       *
 * --------------------------------------------------- */
@RunWith(Suite.class)
@SuiteClasses({
    TaskTest.class,
    EqualsTaskTest.class,
    TaskListTest.class,
    IterableListTest.class,
    EqualsListTest.class,
    TaskIOTest.class
})
public class AllTests { }

