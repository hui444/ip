package duke.commands;

import duke.TaskList;
import duke.common.Errors;
import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.ui.TextUi;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

/**
 * Makes a new list containing keywords given by user
 */
public class FindCommand {
    public static ArrayList<Task> filterByKeyword(String keyword) {
        ArrayList<Task> filteredList = (ArrayList<Task>) TaskList.arrTasks.stream()
                .filter((s) -> s.getDescription().contains(keyword))
                .collect(toList());
        
        return filteredList;
    }

    public static void findTask(String keyword) throws DukeException {
        if(keyword == null) {
            throw new DukeException(Errors.ERROR_NO_KEYWORD);
        }
        ArrayList<Task> filteredTaskList = filterByKeyword(keyword);
        
        if(filteredTaskList.size() == 0) {
            throw new DukeException(Errors.ERROR_NOT_FOUND + keyword);
        }

        TextUi.printLine();
        System.out.println("Here are the matching \"" + keyword + "\" tasks in your list:");
        for (int i = 0; i < filteredTaskList.size(); i++) {
            System.out.println((i+1) + ". " + filteredTaskList.get(i));
        }
        TextUi.printLine();
    }
}
