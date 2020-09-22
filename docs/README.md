# User Guide

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first)
1. Set up the correct JDK version, as follows:
   1. Click `Configure` > `Structure for New Projects` and then `Project Settings` > `Project` > `Project SDK`
   1. If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory where you installed JDK 11
   1. Click `OK`
1. Import the project into Intellij as follows:
   1. Click `Open or Import`.
   1. Select the project directory, and click `OK`
   1. If there are any further prompts, accept the defaults.
1. After the importing is complete, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()`. If the setup is correct, you should see something like the below:
   ```
   ____________________________________________________________
   Hello! I'm Duke
   What can I do for you?
   ____________________________________________________________
   ```
   
## Setting up using the JAR File
Another way to use Duke is by running the JAR File directly.
1. Copy the JAR File into an empty file
1. Open a command window/Terminal in that folder
1. Run the command `java -jar Duke.jar`
1. If the setup is correct, you should see something like the below:
   ```
   ____________________________________________________________
   Hello! I'm Duke
   What can I do for you?
   ____________________________________________________________
   ```

## Features 

### Creates tasks
Duke can create 3 types of tasks:
1. Todo - tasks without a deadline
1. Deadline - tasks with a deadline
1. Event - tasks happening on a specific date/time

### Lists tasks
You can list the tasks in the current task list.

### Marks task as done
After finishing the task, you can mark the specified task as done.

### Deletes task
You can delete the task from the task list.

### Finds task
You can search up tasks in the task list containing a specified keyword.

## Usage

### `todo <TASK DESCRIPTION>` - Adds a todo to the task list

This command adds a todo task to the task list.

Example of usage: 

`todo read book`

Expected outcome:

`____________________________________________________________`
` Got it. I've added this task:`
`   [T][✘] read book`
` Now you have 1 task in the list.`
`____________________________________________________________`

### `deadline <TASK DESCRIPTION> /by <DATE/TIME>` - Adds a deadline to the task list

This command adds a deadline task, including its date/time to the task list.
The date inputted has to be the first argument after `/by` and be in the form of yyyy-mm-dd for Duke to understand.

Example of usage: 

`deadline return book /by Sunday`
`deadline return book /by 2020-09-26 Sunday`

Expected outcome:

`____________________________________________________________`
` Got it. I've added this task:`
`   [D][✘] return book (by: Sunday)`
` Now you have 2 tasks in the list.`
` ____________________________________________________________`

`____________________________________________________________`
`Got it. I've added this task:`
`  [D][✘] return book (by: Sep 26 2020, Sunday)`
`Now you have 3 tasks in the list.`
`____________________________________________________________`

### `event <TASK DESCRIPTION> /at <DATE/TIME>` - Adds an event to the task list

This command adds an event task, including its date/time to the task list.

Example of usage: 

`event project meeting /at Mon 2-4pm`

Expected outcome:

`____________________________________________________________`
` Got it. I've added this task:`
`   [E][✘] project meeting (at: Mon 2-4pm)`
` Now you have 4 tasks in the list.`
`____________________________________________________________`

### `list` - Prints task list

This command lists out all the tasks in the current task list.

Example of usage: 

`list`

Expected outcome:

`____________________________________________________________`
`Here are the tasks in your list:`
`1. [T][✘] read book`
`2. [D][✘] return book (by: Sunday)`
`3. [D][✘] return book (by: Sep 26 2020, Sunday)`
`4. [E][✘] project meeting (at: Mon 2-4pm)`
`____________________________________________________________`

### `done <TASK INDEX>` - Marks a specified task as done

This command marks a specified task in the task list as done.

Example of usage: 

`done 1`

Expected outcome:

`____________________________________________________________`
`Nice! I've marked this task as done: `
`	[T][✓] read book`
`____________________________________________________________`

### `delete <TASK INDEX>` - Deletes a specified task from task list

This command deletes a specified task in the task list as done.

Example of usage: 

`delete 2`

Expected outcome:

`____________________________________________________________`
`Noted. I've removed this task: `
`	[D][✘] return book (by: Sunday)`
`Now you have 3 tasks in the list.`
`____________________________________________________________`

### `find <KEYWORD>` - Finds tasks containing a specified keyword

This command finds all the tasks in the task list containing a specified keyword.

Example of usage: 

`find book`

Expected outcome:

`____________________________________________________________`
`Here are the matching "book" tasks in your list:`
`1. [T][✓] read book`
`2. [D][✘] return book (by: Sep 26 2020, Sunday)`
`____________________________________________________________`

### `bye` - Terminates Duke application

This command exits the program.

Example of usage: 

`bye`

Expected outcome:

`____________________________________________________________`
`Bye. Hope to see you again soon!`
`____________________________________________________________`