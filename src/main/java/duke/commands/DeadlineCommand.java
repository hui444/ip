package duke.commands;

import duke.TaskList;
import duke.ui.TextUi;
import duke.common.Errors;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Task;

/**
 * Adds a deadline to task list.
 */
public class DeadlineCommand {
	public static void addDeadline(String task, boolean isDone, boolean isNew) throws DukeException {
		try {
			if(task == null) {
				throw new DukeException(Errors.ERROR_EMPTY_DEADLINE);
			}

			if(!task.contains("/by")) {
				throw new DukeException(Errors.ERROR_NO_DATE_DEADLINE);
			}

			String description = task.substring(0, task.indexOf("/by"));

			if(description.isEmpty()) {
				throw new DukeException(Errors.ERROR_EMPTY_DEADLINE);
			}

			String deadlineDate = task.substring(task.indexOf("/by") + 3);

			Task deadline = new Deadline(description, deadlineDate);
			TaskList.addTask(deadline);
			if(isDone) {
				deadline.markAsDone();
			}
			if(isNew) {
				TextUi.echoTask(deadline);
			}
		} catch (StringIndexOutOfBoundsException e) {
			TextUi.printError(Errors.ERROR_INVALID_INPUT);
		}
	}
}
