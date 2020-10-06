package duke.parser;

import duke.commands.ListCommand;
import duke.commands.DoneCommand;
import duke.commands.TodoCommand;
import duke.commands.DeadlineCommand;
import duke.commands.EventCommand;
import duke.commands.DeleteCommand;
import duke.commands.FindCommand;
import duke.storage.Storage;
import duke.TaskList;

import duke.common.Commands;
import duke.common.Errors;

import duke.exceptions.DukeException;
import duke.ui.TextUi;

import java.io.IOException;

/**
 * Parses user input
 */
public class Parser {
    public static void handleTasks(String line) {
        String[] taskLine = splitCommandAndTask(line);
        String command = taskLine[0].toLowerCase();
        String task = taskLine[1];
        String filePath = "data/duke.txt";

        try {
            switch (command) {
            case Commands.COMMAND_LIST:
                //display list
                ListCommand.printList();
                break;
            case Commands.COMMAND_DONE:
                //mark task as done
                DoneCommand.markAsDone(task);
                break;
            case Commands.COMMAND_TODO:
                TodoCommand.addTodo(task, false, true);
                break;
            case Commands.COMMAND_DEADLINE:
                DeadlineCommand.addDeadline(task, false, true);
                break;
            case Commands.COMMAND_EVENT:
                EventCommand.addEvent(task, false, true);
                break;
            case Commands.COMMAND_DELETE:
                DeleteCommand.deleteTask(task);
                break;
            case Commands.COMMAND_FIND:
                FindCommand.findTask(task);
                break;
            default:
                throw new DukeException(Errors.ERROR_UNRECOGNISED_INPUT);
            }
        } catch (NumberFormatException e) {
            TextUi.printError(Errors.ERROR_INVALID_INPUT);
        } catch (IndexOutOfBoundsException e) {
            TextUi.printError("Number is out of range!");
        } catch (DukeException e) {
            TextUi.printError(e.errorMessage);
        }

        try {
            Storage.writeFile(filePath, Storage.newDukeList());
        } catch (IOException e) {
            TextUi.printError(e.getMessage() + "\n Please create a file named "
                    + filePath + " !");
        }
	}
	
    /**
     * Splits task inputted by user into understandable Strings.
     *
     * @param args Arguments supplied by the user.
     * @return Returns command and task descriptions.
     */
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
        if (itemIndex < 0 || itemIndex >= TaskList.arrTasks.size()) {
            throw new DukeException(Errors.ERROR_INVALID_TASK_NUMBER);
        }
        return itemIndex;
    }
}
