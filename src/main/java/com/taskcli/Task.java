package com.taskcli;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//creates a new task
public class Task 
{
    private String maintask;
    private LocalDateTime createddate;
    private LocalDateTime expectedcompletiondate;
    private LocalDateTime duedate;
    private LocalDateTime completiondate;
    private String status;
    private boolean done;
    private List<subtask> subtasks; 
    Scanner tsk=new Scanner (System.in);

    public Task(String name) 
    {
        this.maintask = name;
        this.done = false;
        this.createddate = LocalDateTime.now();
        this.subtasks = new ArrayList<>();
    }

    public void addsubtask(String s) 
    { // new subtask with name as only field
        System.out.print("Add more details?: Y or N: ");
        if (tsk.nextLine().charAt(0)=='Y')
        {
            System.out.print("Enter status for subtask: ");
            String stat=tsk.nextLine();

            //date time format for due date time
            DateTimeFormatter dmyhm = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            LocalDateTime duedatetime = null; 
            while (duedatetime==null)
            {      
                System.out.println("Enter date and time in format (DD-MM-YYYY HH:MM): ");
                String dd = tsk.nextLine().trim();
                try {
                    duedatetime = LocalDateTime.parse(dd, dmyhm);
                    System.out.println("Success! Date saved: " + duedatetime);
                    } 
                catch (DateTimeParseException e) 
                {
                    System.out.println("Error: Invalid format. Please make sure to follow 'dd-MM-yyyy HH:mm'");
                }
            }
            subtasks.add(new subtask(s,stat,duedatetime));
        }
        else
            subtasks.add(new subtask(s));
    }

    public void setDone(boolean done) 
    {
        this.done = done;
        if (done) 
            this.completiondate = LocalDateTime.now();
         else 
            this.completiondate = null;
    }

    public void setStatus(String s)
    {
        this.status=s;
    }

    public void setExpectedcompletiondate(LocalDateTime date)
    {
        this.expectedcompletiondate = date;
    }

    public void setDuedate(LocalDateTime date)
    {
        this.duedate = date;
    }

    public String getMaintask()
    {
        return maintask;
    }

    public boolean isDone()
    {
        return done;
    }

    public List<subtask> getSubtasks()
    {
        return subtasks;
    }

    public LocalDateTime getCreateddate()
    {
        return createddate;
    }



    public LocalDateTime getExpectedcompletiondate()
    {
        return expectedcompletiondate;
    }

    public LocalDateTime getCompleteddate()
    {
        return completiondate;
    }

    public LocalDateTime getDuedate()
    {
        return duedate;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setCreateddate(LocalDateTime date) 
    {
        this.createddate = date;
    }

    public void setCompletiondate(LocalDateTime date) 
    {
        this.completiondate = date;
    }

    public String toFileFormat() 
    {
        String exp = "null";
        String comp = "null";
        String stat = "null";
        if (expectedcompletiondate != null)
            exp = expectedcompletiondate.toString();
        if (completiondate != null)
            comp = completiondate.toString();
        if (status != null)
            stat = status;
        return "T|" + maintask + "|" + done + "|" + createddate.toString() + "|" + exp + "|" + duedate.toString() + "|" + comp + "|" + stat;
    }
    

    @Override
    public String toString() 
    {
        String pr = "";
        String stat = (status != null) ? status : "";
        String expComp = (expectedcompletiondate != null) ? expectedcompletiondate.toString() : "NA";

        if (done)
            pr = "[X] " + maintask + " (Completed Date: " + completiondate + ") " + stat;
        else
            pr = "[ ] " + maintask + " (Due: " + duedate + ") " + stat + " (Expected Completion: " + expComp + ") Created Date: " + createddate;

        if (!subtasks.isEmpty()) 
            for (subtask st : subtasks) 
                pr += "\n        -> " + st.toString(); 
        return pr;
    }
}
