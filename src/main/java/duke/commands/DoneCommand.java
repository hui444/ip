package duke.commands;

import duke.parser.Parser;
import duke.TaskList;
import duke.ui.TextUi;
import duke.common.Errors;
import duke.exceptions.DukeException;

/**
 * Marks specified task in task list as done.
 */
public class DoneCommand {
    public static void markAsDone(String inputNum) throws DukeException {
        try {
            int itemIndex = Parser.getIndexFromInput(inputNum,
                    "Please input a number to mark task as done.");
            if(TaskList.arrTasks.get(itemIndex).getStatusIcon().equals("[\u2713]")) {
                throw new DukeException(Errors.ERROR_MARKED_TASK);
            }
            TaskList.arrTasks.get(itemIndex).markAsDone();
            TextUi.printDone(itemIndex);
        } catch (NumberFormatException e) {
            TextUi.printError(Errors.ERROR_INVALID_TASK_NUMBER);
        }
    }
}
