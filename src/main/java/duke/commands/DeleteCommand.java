package duke.commands;

import duke.parser.Parser;
import duke.TaskList;
import duke.ui.TextUi;
import duke.exceptions.DukeException;

/**
 * Deletes selected task from task list.
 */
public class DeleteCommand {
	public static void deleteTask(String inputNum) throws DukeException {
		try {
			int deletedItemIndex = Parser.getIndexFromInput(inputNum,
					"Please input a number to delete task.");
			TextUi.printLine();
			System.out.println("Noted. I've removed this task:  \n\t"
					+ TaskList.arrTasks.get(deletedItemIndex));
			TaskList.arrTasks.remove(deletedItemIndex);
			String wordTask = (TaskList.arrTasks.size() > 1) ? " tasks" : " task";
			System.out.println("Now you have " + TaskList.arrTasks.size() + wordTask + " in the list.");
			TextUi.printLine();
		} catch (DukeException err) {
			throw err;
		}
	}
}
