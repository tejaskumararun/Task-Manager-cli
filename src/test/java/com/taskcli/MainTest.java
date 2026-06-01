package com.taskcli;

import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class MainTest 
{
    @Test
    public void testTaskToFileFormat() 
    {
        Task t = new Task("Project");
        t.setDuedate(LocalDateTime.of(2026, 12, 31, 23, 59));
        String result = t.toFileFormat();
        
        // Verify format: T|name|done|created|expected|due|comp|stat
        assertTrue(result.contains("T|Project|false"));
        assertTrue(result.contains("2026-12-31T23:59"));
    }
}