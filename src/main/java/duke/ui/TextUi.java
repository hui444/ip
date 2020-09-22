package duke.ui;

import duke.TaskList;
import duke.tasks.Task;

/**
 * Text UI of the Duke
 */
public class TextUi {

    public static final String MESSAGE_GREET_SIGN = "____________________________________________________________\n"
            + "Hello! I'm Duke\n"
            + "What can I do for you?\n"
            + "____________________________________________________________";

    public static final String MESSAGE_BYE_SIGN = "____________________________________________________________\n"
            + "Bye. Hope to see you again soon!\n"
            + "____________________________________________________________\n";

    public static final String HORIZONTAL_LINE = "____________________________________________________________";

    public static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printError(String errorMessage) {
        printLine();
        System.out.println(errorMessage);
        printLine();
    }

    public static void printDone(int doneItemIndex) {
        printLine();
        System.out.println("Nice! I've marked this task as done: \n\t"
                + TaskList.arrTasks.get(doneItemIndex));
        printLine();
	}

    /**
     * Echos task added to list and prints number of task in list.
     *
     * @param task Task contains description of inputted task.
     */
    public static void echoTask(Task task) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        String wordTask = (TaskList.arrTasks.size() > 1) ? " tasks" : " task";
        System.out.println("Now you have " + TaskList.arrTasks.size() + wordTask + " in the list.");
        printLine();
    }
}
