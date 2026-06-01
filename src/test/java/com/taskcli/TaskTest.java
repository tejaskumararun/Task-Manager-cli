package com.taskcli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TaskTest
{
    @Test
    public void testTaskInitialization()
    {
        Task t = new Task("Test Task");
        assertEquals("Test Task", t.getMaintask());
        assertFalse(t.isDone());
        assertNotNull(t.getCreateddate());
    }

    @Test
    public void testSetDoneUpdatesCompletionDate()
    {
        Task t = new Task("Test Taskm");
        t.setDone(true);
        assertTrue(t.isDone());
        assertNotNull(t.getCompleteddate());
    }
}