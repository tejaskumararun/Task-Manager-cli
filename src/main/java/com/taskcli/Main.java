package com.taskcli;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main 
{
    private static final String FILE_NAME = "tasks.txt";
    private static ArrayList<Task> tasklist = new ArrayList<>();

    public static void main(String[] args) 
    {
        loadTasksFromFile();
        Scanner sc = new Scanner(System.in);

        System.out.println("\t\t Task Manager CLI");
        boolean continuerun =true;
        while (continuerun) 
        {
            System.out.print("\nEnter choice: (1) View Tasks  (2) Add Task  (3) Quit");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) 
            {
                case 1-> printTasks();
                case 2->  {   tasklist.add(createTask());
                                saveTasksToFile();
                                System.out.println("Task saved");
                            }
                case 3-> continuerun=false;
                default -> System.out.println("Invalid choice");
            }
        }
        sc.close();
    }
    private static Task createTask()
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter task name: ");
        String n=sc.nextLine();

        Task newTask = new Task(n);

        // Set Due Date
        DateTimeFormatter dmyhm = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime dd=null;

        while (dd == null) 
        {
            System.out.print("Enter due date time (DD-MM-YYYY hh:mm): ");
            String input = sc.nextLine().trim(); // Safe to use next() because there are no spaces
            try 
            {
                dd = LocalDateTime.parse(input, dmyhm);
            }
            catch (DateTimeParseException e)
             {
                System.out.println("Invalid format. Try again.");
            }
        }
        newTask.setDuedate(dd);

        //Set Status
        System.out.print("Add status Y or N: ");
        if (sc.nextLine().charAt(0)=='Y')
        {
            System.out.print("Enter status: ");
            String stat=sc.nextLine();
            newTask.setStatus(stat);
        }

        //Set completion date time
        System.out.print("Add expected compeletion date time?: Y or N");
        String choice=sc.nextLine().trim();
        if (choice.charAt(0)=='Y')
        {
            LocalDateTime edt = null;    
            while (edt==null)
            {   
                System.out.println("Enter expected completion date and time in format (DD-MM-YYYY HH:MM): ");
                String ed = sc.nextLine().trim();

                try {
                    edt = LocalDateTime.parse(ed, dmyhm);
                    System.out.println("Success! Date saved: " + edt);
                    } 
                catch (DateTimeParseException e) 
                {
                    System.out.println("Error: Invalid format. Please make sure to follow 'dd-MM-yyyy HH:mm'.");
                }
            }
            newTask.setExpectedcompletiondate(edt);
        }

        System.out.print("Add subtasks?: 1 for Y or 0 for N");
        int chs=sc.nextInt();
        sc.nextLine();
        while (chs!=0)
        {
            System.out.print("Enter subtask name: ");
            String sn=sc.nextLine();
            newTask.addsubtask(sn);
            System.out.print("Add more subtasks? 1 or 0: ");
            chs=sc.nextInt();
            sc.nextLine();
        }
        
        return newTask;
    }

    private static void printTasks() 
    {
        if (tasklist.isEmpty()) 
            System.out.println("You have no tasks right now.");
        else 
            for (int i = 0; i < tasklist.size(); i++) 
                System.out.println((i + 1) + " " + tasklist.get(i).toString());
    }

    // Reads the .txt file 
    private static void loadTasksFromFile() 
    {
        File file = new File(FILE_NAME);

        if (!file.exists()) 
        {
            System.out.println("No save file found. Starting fresh.");
            return;
        }

        try {
            Scanner fr = new Scanner(file);
            Task lastTask = null;

            while (fr.hasNextLine()) {
                String line = fr.nextLine();
                String[] parts = line.split("\\|");

                if (parts[0].equals("T") && parts.length == 8) {
                    String name = parts[1];
                    boolean done = Boolean.parseBoolean(parts[2]);
                    LocalDateTime created = LocalDateTime.parse(parts[3]);

                    LocalDateTime expected = null;
                    if (!parts[4].equals("null"))
                        expected = LocalDateTime.parse(parts[4]);

                    LocalDateTime due = LocalDateTime.parse(parts[5]);

                    LocalDateTime completed = null;
                    if (!parts[6].equals("null"))
                        completed = LocalDateTime.parse(parts[6]);

                    String stat = null;
                    if (!parts[7].equals("null"))
                        stat = parts[7];

                    Task t = new Task(name);
                    t.setCreateddate(created);
                    t.setDuedate(due);
                    t.setDone(done);
                    t.setCompletiondate(completed);
                    t.setExpectedcompletiondate(expected);
                    t.setStatus(stat);

                    tasklist.add(t);
                    lastTask = t;

                } 
                else if (parts[0].equals("S") && parts.length == 5 && lastTask != null) 
                {
                    String sname = parts[1];
                    boolean sdone = Boolean.parseBoolean(parts[2]);

                    String sstat = null;
                    if (!parts[3].equals("null"))
                        sstat = parts[3];

                    LocalDateTime sdt = null;
                    if (!parts[4].equals("null"))
                        sdt = LocalDateTime.parse(parts[4]);

                    subtask st;
                    if (sstat != null && sdt != null)
                        st = new subtask(sname, sstat, sdt);
                    else
                        st = new subtask(sname);

                    if (sdone)
                        st.setDone(true);

                    lastTask.getSubtasks().add(st);
                }
            }
            fr.close();
            System.out.println("Loaded " + tasklist.size() + " task(s).");
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("No save file found. Starting fresh.");
        }
    }

    // Writes the list back to the .txt file
    private static void saveTasksToFile() 
    {
        try {
            FileWriter w = new FileWriter(FILE_NAME);

            for (int i = 0; i < tasklist.size(); i++) 
            {
                Task t = tasklist.get(i);
                w.write(t.toFileFormat() + "\n");
                //write subtasks
                for (int j = 0; j < t.getSubtasks().size(); j++) 
                    w.write(t.getSubtasks().get(j).toFileFormat() + "\n");
            }
            w.close();
            System.out.println("Tasks saved successfully.");
        } 
        catch (IOException e) 
        {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}

