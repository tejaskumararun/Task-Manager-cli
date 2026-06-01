package com.taskcli;

import java.time.LocalDateTime;

public class subtask 
{
    private String name;
    private boolean done;
    private String status;
    private LocalDateTime duedatetime;

    public subtask(String name) 
    {  
        this.name = name;
        this.done = false;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public subtask(String name, String status, LocalDateTime duedatetime) 
    {
        this.name = name;
        this.done = false;
        this.status=status;
        this.duedatetime=duedatetime;
    }


    public String getName() 
    {
        return name;
    }

    public boolean isDone() 
    {
        return done;
    }

    public String getStatus() 
    {
        return status;
    }

    public LocalDateTime getDuedatetime() 
    {
        return duedatetime;
    }

    public void setDone(boolean done) 
    {
        this.done = done;
        this.status="Completed";
    }
    public void setStatus(String stat) 
    {
        this.status = stat;
    }
    public void setDuedate(LocalDateTime dd) 
    {
        this.duedatetime = dd;
    }

    @Override
    public String toString() 
    {
        return (done ? "[X] " : "[ ] ") + name +" (Due: "+ duedatetime +")";
    }

    public String toFileFormat() 
    {
        String stat = (status != null) ? status : "null";
        String dt = (duedatetime != null) ? duedatetime.toString() : "null";
        return "S|" + name + "|" + done + "|" + stat + "|" + dt;
    }
} 
