package duke;

import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Initialises and adds tasks to task list
 */
public class TaskList {
	public static ArrayList<Task> arrTasks = new ArrayList<>();

	public static void addTask(Task task) {
		arrTasks.add(task);
	}
}
