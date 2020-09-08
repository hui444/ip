package duke;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.util.Scanner;

public class Duke {
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_BYE = "bye";

    private static final int MAX_TASKS = 100;

    private static final String MESSAGE_GREET_SIGN = "____________________________________________________________\n"
            + "Hello! I'm Duke\n"
            + "What can I do for you?\n"
            + "____________________________________________________________\n";
    private static final String MESSAGE_BYE_SIGN = "____________________________________________________________\n"
            + "Bye. Hope to see you again soon!\n"
            + "____________________________________________________________\n";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    public static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public static String printEmptyError(String errTask) {
        return "☹ OOPS!!! The description of a " + errTask + " cannot be empty.";
    }

    public static void printError(String errorMessage) {
        printLine();
        System.out.println(errorMessage);
        printLine();
    }

    private static Task[] taskList = new Task[MAX_TASKS];
    private static int taskListNum = 0;

    public static void printList() {
        if (taskListNum == 0) {
            printError("List is empty.");
            return;
        }
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskListNum; i++) {
            System.out.println((i+1) + ". " + taskList[i].toString());
        }
        printLine();
    }

    public static void addTask(Task task) {
        taskList[taskListNum] = task;
        taskListNum++;
    }

    public static void addTodo(String task) {
        try {
            if(task == null) {
                throw new DukeException(printEmptyError(COMMAND_TODO));
            }
            Task todo = new ToDo(task);
            addTask(todo);
            echoTask(todo);
        } catch (StringIndexOutOfBoundsException e) {
            printError("Input error!");
        } catch (DukeException e) {
            printLine();
            System.out.println(e);
            printLine();
        }

    }

    public static void addDeadline(String task) {
        try {
            if(task == null) {
                throw new DukeException(printEmptyError(COMMAND_DEADLINE));
            }
            if(!task.contains("/by")) {
                throw new DukeException("Please input a date for the deadline!");
            }
            String description = task.substring(0, task.indexOf("/by"));
            String deadlineDate = task.substring(task.indexOf("/by") + 3);

            Task deadline = new Deadline(description, deadlineDate);
            addTask(deadline);
            echoTask(deadline);
        } catch (StringIndexOutOfBoundsException e) {
            printError("Input error!");
        } catch (DukeException e) {
            printLine();
            System.out.println(e);
            printLine();
        }
    }

    public static void addEvent(String task) {
        try {
            if (task == null) {
                throw new DukeException(printEmptyError(COMMAND_EVENT));
            }
            if(!task.contains("/at")) {
                throw new DukeException("Please input a date for this event!");
            }
            String description = task.substring(0, task.indexOf("/at"));
            String eventDate = task.substring(task.indexOf("/at") + 3);

            Task event = new Event(description, eventDate);
            addTask(event);
            echoTask(event);
        } catch (StringIndexOutOfBoundsException e) {
            printError("Input error!");
        } catch (DukeException e) {
            printLine();
            System.out.println(e);
            printLine();
        }
    }

    public static void echoTask(Task task) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        String wordTask = (taskListNum > 1) ? " tasks" : " task";
        System.out.println("Now you have " + taskListNum + wordTask + " in the list.");
        printLine();
    }

    public static void markAsDone(String inputNum) {
        try {
            if (inputNum == null) {
                throw new DukeException("☹ OOPS!!! Please input a number to mark task as done.");
            }
            int index = Integer.parseInt(inputNum) - 1;
            if (index < 0 || index >= taskListNum) {
                throw new DukeException("Invalid task number.");
            }
            if(taskList[index].getStatusIcon().equals("[" + "\u2713" + "]")) {
                throw new DukeException("This task is already marked as done!");
            }
            taskList[index].markAsDone();
            printDone(taskList[index]);
        } catch (NumberFormatException e) {
            printError("Invalid task number.");
        } catch (DukeException e) {
            printLine();
            System.out.println(e);
            printLine();
        }
    }

    public static void printDone(Task doneItem) {
        printLine();
        System.out.println("Nice! I've marked this task as done: \n"
                + doneItem.getStatusIcon()  + " "
                + doneItem.description);
        printLine();
    }

    public static String[] splitCommandAndTask(String args) {
        final String[] line = args.trim().split(" ",2);
        if (line.length == 2) {
            return line;
        }
        else {
            return new String[] {line[0], null};
        }
    }

    public static void handleTasks() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals(COMMAND_BYE)) {
            //split task n line
            String[] taskLine = splitCommandAndTask(line);
            String command = taskLine[0];
            String task = taskLine[1];

            try {
                switch (command) {
                case COMMAND_LIST:
                    //display list
                    printList();
                    break;
                case COMMAND_DONE:
                    //mark task as done
                    markAsDone(task);
                    break;
                case COMMAND_TODO:
                    addTodo(task);
                    break;
                case COMMAND_DEADLINE:
                    addDeadline(task);
                    break;
                case COMMAND_EVENT:
                    addEvent(task);
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (NumberFormatException e) {
                printError("Invalid input!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Number is out of range!");
            } catch (DukeException e) {
                printLine();
                System.out.println(e);
                printLine();
            }

            in = new Scanner(System.in);
            line = in.nextLine();
            }
        }

    public static void main(String[] args) {
        System.out.println(MESSAGE_GREET_SIGN);
        handleTasks();
        System.out.println(MESSAGE_BYE_SIGN);
    }
}
