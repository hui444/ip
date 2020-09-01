import java.util.Scanner;

public class Duke {
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_BYE = "bye";

    private static final int MAX_TASKS = 100;

    private static final String GREET_SIGN = "____________________________________________________________\n"
            + "Hello! I'm Duke\n"
            + "What can I do for you?\n"
            + "____________________________________________________________\n";
    private static final String BYE_SIGN = "____________________________________________________________\n"
            + "Bye. Hope to see you again soon!\n"
            + "____________________________________________________________\n";

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    private static Task[] taskList = new Task[MAX_TASKS];
    private static int taskListNum = 0;

    public static void echo(String word) {
        printLine();
        System.out.println("added: " + word + " \n");
        printLine();
    }

    public static void printList() {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < taskListNum; i++) {
            System.out.println((i+1) + ". " + taskList[i].toString());
        }
        printLine();
    }

    public static void addTask(Task task) {
        taskList[taskListNum] = task;
        taskListNum++;
    }

    public static void addTodo(String task) {
        Task t = new ToDo(task);
        addTask(t);
        echoTask(t);
    }

    public static void addDeadline(String task) {
        String description = task.substring(0, task.indexOf("/by"));
        String by = task.substring(task.indexOf("/by")+3);

        Task t = new Deadline(description, by);
        addTask(t);
        echoTask(t);
    }

    public static void addEvent(String task) {
        String description = task.substring(0, task.indexOf("/at"));
        String at = task.substring(task.indexOf("/at")+3);

        Task t = new Event(description, at);
        addTask(t);
        echoTask(t);
    }

    public static void echoTask(Task task) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        if(taskListNum > 1) {
            System.out.println("Now you have " + taskListNum + " tasks in the list.");
        }
        else {
            System.out.println("Now you have " + taskListNum + " task in the list.");
        }
        printLine();
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
        if(line.length == 2) {
            return line;
        }
        else {
            return new String[] {line[0], ""};
        }
    }
    public static void handleTasks() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals(COMMAND_BYE)) {
            if(line.equals(COMMAND_LIST)){
                //display list
                printList();
            }
            else if(line.contains(COMMAND_DONE)) {
                //mark task as done
                String[] doneTask = splitCommandAndTask(line);
                int index = Integer.parseInt(doneTask[1]) - 1;
                taskList[index].markAsDone();
                printDone(taskList[index]);
            } else {
                //add item to list
                taskList[taskListNum] = new Task(line);

                if(line.contains(COMMAND_TODO)) {
                    //add task without date/time attached to it
                    String[] todoTask = splitCommandAndTask(line);
                    addTodo(todoTask[1]);
                } else if(line.contains(COMMAND_DEADLINE)) {
                    //add task that needs to be done before a specific date/time
                    String[] deadlineTask = splitCommandAndTask(line);
                    addDeadline(deadlineTask[1]);
                } else if(line.contains(COMMAND_EVENT)) {
                    //add task that needs to be done before a specific date/time
                    String[] eventTask = splitCommandAndTask(line);
                    addEvent(eventTask[1]);
                }
                else {
                    //echo command entered by user
                    echo(line);
                }
            }
            in = new Scanner(System.in);
            line = in.nextLine();
        }
    }

    public static void main(String[] args) {
        System.out.println(GREET_SIGN);
        handleTasks();
        System.out.println(BYE_SIGN);
    }
}
