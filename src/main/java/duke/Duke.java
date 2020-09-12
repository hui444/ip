package duke;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_DELETE = "delete";

    private static final int MAX_TASKS = 100;

    private static final String MESSAGE_GREET_SIGN = "____________________________________________________________\n"
            + "Hello! I'm Duke\n"
            + "What can I do for you?\n"
            + "____________________________________________________________\n";
    private static final String MESSAGE_BYE_SIGN = "____________________________________________________________\n"
            + "Bye. Hope to see you again soon!\n"
            + "____________________________________________________________\n";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    private static final String ERROR_EMPTY_TODO = "☹ OOPS!!! The description of a todo cannot be empty.";
    private static final String ERROR_EMPTY_DEADLINE = "☹ OOPS!!! The description of a deadline cannot be empty.";
    private static final String ERROR_EMPTY_EVENT = "☹ OOPS!!! The description of an event cannot be empty.";
    private static final String ERROR_EMPTY_LIST = "List is empty.";
    private static final String ERROR_INVALID_INPUT = "Invalid input!";
    private static final String ERROR_UNRECOGNISED_INPUT = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String ERROR_INVALID_TASK_NUMBER = "Invalid task number.";
    private static final String ERROR_NO_DATE_DEADLINE = "Please input a date for the deadline!";
    private static final String ERROR_NO_DATE_EVENT = "Please input a date for this event!";
    private static final String ERROR_MARKED_TASK = "This task has already marked as done!";

    public static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printError(String errorMessage) {
        printLine();
        System.out.println(errorMessage);
        printLine();
    }

    private static ArrayList<Task> arrTasks= new ArrayList<>();

    public static void printList() {
        if (arrTasks.size() == 0) {
            printError(ERROR_EMPTY_LIST);
            return;
        }
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < arrTasks.size(); i++) {
            System.out.println((i+1) + ". " + arrTasks.get(i));
        }
        printLine();
    }

    public static void addTask(Task task) {
        arrTasks.add(task);
    }

    public static void addTodo(String task) throws DukeException {
        try {
            if(task == null) {
                throw new DukeException(ERROR_EMPTY_TODO);
            }
            Task todo = new ToDo(task);
            addTask(todo);
            echoTask(todo);
        } catch (StringIndexOutOfBoundsException e) {
            printError(ERROR_INVALID_INPUT);
        }

    }

    public static void addDeadline(String task) throws DukeException {
        try {
            if(task == null) {
                throw new DukeException(ERROR_EMPTY_DEADLINE);
            }
            if(!task.contains("/by")) {
                throw new DukeException(ERROR_NO_DATE_DEADLINE);
            }
            String description = task.substring(0, task.indexOf("/by"));
            String deadlineDate = task.substring(task.indexOf("/by") + 3);

            Task deadline = new Deadline(description, deadlineDate);
            addTask(deadline);
            echoTask(deadline);
        } catch (StringIndexOutOfBoundsException e) {
            printError(ERROR_INVALID_INPUT);
        }
    }

    public static void addEvent(String task) throws DukeException {
        try {
            if (task == null) {
                throw new DukeException(ERROR_EMPTY_EVENT);
            }
            if(!task.contains("/at")) {
                throw new DukeException(ERROR_NO_DATE_EVENT);
            }
            String description = task.substring(0, task.indexOf("/at"));
            String eventDate = task.substring(task.indexOf("/at") + 3);

            Task event = new Event(description, eventDate);
            addTask(event);
            echoTask(event);
        } catch (StringIndexOutOfBoundsException e) {
            printError(ERROR_INVALID_INPUT);
        }
    }

    public static void echoTask(Task task) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        String wordTask = (arrTasks.size() > 1) ? " tasks" : " task";
        System.out.println("Now you have " + arrTasks.size() + wordTask + " in the list.");
        printLine();
    }

    public static void markAsDone(String inputNum) throws DukeException {
        try {
            int itemIndex = getIndexFromInput(inputNum,
                    "Please input a number to mark task as done.");
            if(arrTasks.get(itemIndex).getStatusIcon().equals("[\u2713]")) {
                throw new DukeException(ERROR_MARKED_TASK);
            }
            arrTasks.get(itemIndex).markAsDone();
            printDone(itemIndex);
        } catch (NumberFormatException e) {
            printError(ERROR_INVALID_TASK_NUMBER);
        }
    }

    public static void printDone(int doneItemIndex) {
        printLine();
        System.out.println("Nice! I've marked this task as done: \n\t"
                + arrTasks.get(doneItemIndex));
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

    public static int getIndexFromInput(String inputNum, String nullErrMessage) throws DukeException {
        if(inputNum == null) {
            throw new DukeException(nullErrMessage);
        }
        int itemIndex = Integer.parseInt(inputNum) - 1;
        if (itemIndex < 0 || itemIndex >= arrTasks.size()) {
            throw new DukeException(ERROR_INVALID_TASK_NUMBER);
        }
        return itemIndex;
    }

    public static void deleteTask(String inputNum) throws DukeException {
        try {
            int deletedItemIndex = getIndexFromInput(inputNum,
                    "Please input a number to delete task.");
            printLine();
            System.out.println("Noted. I've removed this task:  \n\t"
                    + arrTasks.get(deletedItemIndex));
            arrTasks.remove(deletedItemIndex);
            String wordTask = (arrTasks.size() > 1) ? " tasks" : " task";
            System.out.println("Now you have " + arrTasks.size() + wordTask + " in the list.");
            printLine();
        } catch (DukeException err) {
            throw err;
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
                case COMMAND_DELETE:
                    deleteTask(task);
                    break;
                default:
                    throw new DukeException(ERROR_UNRECOGNISED_INPUT);
                }
            } catch (NumberFormatException e) {
                printError(ERROR_INVALID_INPUT);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Number is out of range!");
            } catch (DukeException e) {
                printLine();
                System.out.println(e.errorMessage);
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
