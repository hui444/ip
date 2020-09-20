package duke.commands;

import duke.TaskList;
import duke.ui.TextUi;
import duke.common.Errors;

/**
 * Prints task list.
 */
public class ListCommand {
	public static void printList() {
		if (TaskList.arrTasks.size() == 0) {
			TextUi.printError(Errors.ERROR_EMPTY_LIST);
			return;
		}
		TextUi.printLine();
		System.out.println("Here are the tasks in your list:");
		for (int i = 0; i < TaskList.arrTasks.size(); i++) {
			System.out.println((i+1) + ". " + TaskList.arrTasks.get(i));
		}
		TextUi.printLine();
	}
}
