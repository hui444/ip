package duke.commands;

import duke.TaskList;
import duke.ui.TextUi;
import duke.common.Errors;
import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * Adds a todo to task list.
 */
public class TodoCommand {
    public static void addTodo(String task, boolean isDone, boolean isNew) throws DukeException {
        try {
            if(task == null) {
                throw new DukeException(Errors.ERROR_EMPTY_TODO);
            }
            Task todo = new ToDo(task);
            TaskList.addTask(todo);
            if(isDone) {
                todo.markAsDone();
            }
            if(isNew) {
                TextUi.echoTask(todo);
            }
        } catch (StringIndexOutOfBoundsException e) {
            TextUi.printError(Errors.ERROR_INVALID_INPUT);
        }
    }
}
