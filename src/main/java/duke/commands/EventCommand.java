package duke.commands;

import duke.TaskList;
import duke.ui.TextUi;
import duke.common.Errors;
import duke.exceptions.DukeException;
import duke.tasks.Event;
import duke.tasks.Task;

/**
 * Adds an event to task list.
 */
public class EventCommand {
	public static void addEvent(String task, boolean isDone, boolean isNew) throws DukeException {
		try {
			if (task == null) {
				throw new DukeException(Errors.ERROR_EMPTY_EVENT);
			}
			if(!task.contains("/at")) {
				throw new DukeException(Errors.ERROR_NO_DATE_EVENT);
			}
			String description = task.substring(0, task.indexOf("/at"));

			if(description.isEmpty()) {
				throw new DukeException(Errors.ERROR_EMPTY_EVENT);
			}

			String eventDate = task.substring(task.indexOf("/at") + 3);

			Task event = new Event(description, eventDate);
			TaskList.addTask(event);
			if(isDone) {
				event.markAsDone();
			}
			if(isNew) {
				TextUi.echoTask(event);
			}
		} catch (StringIndexOutOfBoundsException e) {
			TextUi.printError(Errors.ERROR_INVALID_INPUT);
		}
	}
}
