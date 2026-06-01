package com.taskcli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class SubtaskTest {
    @Test
    public void testSubtaskCompletion() 
    {
        subtask st = new subtask("Test the project subtask");
        st.setDone(true);
        assertTrue(st.isDone());
        assertEquals("Completed", st.getStatus());
    }

    @Test
    public void testFileFormatExport() {
        subtask st = new subtask("Test", "Pending", null);
        String format = st.toFileFormat();
        // S|Name|done|status|date
        assertTrue(format.startsWith("S|Test|false|Pending|null"));
    }
}