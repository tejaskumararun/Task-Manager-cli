# Task Manager CLI
    Command line interface based task manager 

    Allows users to track tasks and subtasks, modify them and includes local file persistence.
    
## Task Fields Include:
    1. Name
    2. Date of creation
    3. Due Date
    4. Mark as Done
**Optional Task fields**
    1. Expected Date of Completion
    2. Date of Completion
    3. Custom Status Messages
    4. Multiple Subtasks

### Sub Task Fields Include:
    1. Name
    2. Mark as Done
**Optional Subtask fields**
    1. Custom Status Messages
    2. Due Date
   
## Features Include:
>1. Persistent Local Storage: Automatically saves all data to tasks.txt and loads it upon startup.

>2. Task Management:
    Create tasks with custom names and due dates.
    Set statuses and track expected completion times.
    Toggle completion status (mark as done/not done).

    Subtask Options:
        Attach hierarchical subtasks to main tasks.
        Track individual subtask details, including status and due dates.

>3. Data Validation & Integrity:
        Enforces DD-MM-YYYY HH:mm format for date inputs
        Handles input errors using try-catch blocks


## Installation
**Clone the repository:**
`git clone https://github.com/tejaskumararun/Task-Manager-cli.git`
    
**Enter directory**
`cd Task-Manager-cli`

>Run without Maven:
**Compile**
`javac -d bin src/main/java/com/taskcli/*.java`

**Run**
`java -cp bin com.taskcli.Main`



>Run with Maven:
**Build the project:**
    mvn clean package

**Run the application:**
   `java -jar target/task-cli-1.0-SNAPSHOT-jar-with-dependencies.jar`

**Use Unit test cases(if needed)**
    mvn test

### Tech Stack
>Language: Java 17
>Build Tool: Apache Maven
>Testing: JUnit 4


## Version History:
| Version | Date        | Changes                                                                         |
| v1.1.1  | 1 Jun 2026  | Added Readme.md                                                                 |
| v1.1.0  | 1 Jun 2026  | Added option to modify task and subtask names                                   |
| v1.0.9  | 1 Jun 2026  | Added option to modify tasks and subtasks, status duedate, mark done, etc       |
| v1.0.8  | 1 Jun 2026  | Improved CLI readability |     
| v1.0.7  | 1 Jun 2026  | Added unit test cases and verified |
| v1.0.6  | 1 Jun 2026  | Added .gitignore |
| v1.0.1  | 1 Jun 2026  | Fixed input errors: DayDateTime parse issues and Scanner issues|
| v1.0.0  | 1 Jun 2026  | Initial release: Task Manager CLI with file persistence and hierarchical structure|


