package duke.storage;

import duke.TaskList;
import duke.commands.DeadlineCommand;
import duke.commands.EventCommand;
import duke.commands.TodoCommand;
import duke.exceptions.DukeException;
import duke.ui.TextUi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Deals with actions regarding storage file.
 */
public class Storage {
	public static void writeFile(String filePath, String text) throws IOException {
		FileWriter fileWriter = new FileWriter(filePath);
		fileWriter.write(text);
		fileWriter.close();
	}

	/**
	 * Creates a new set of task information as per user input.
	 *
	 * @return Returns a new String consisting of the current task list.
	 */
	public static String newDukeList() {
		StringBuilder dukeText = new StringBuilder();

		for(int i = 0; i < TaskList.arrTasks.size(); i++) {
			dukeText.append(TaskList.arrTasks.get(i).fileString() + "\n");
		}
		return dukeText.toString();
	}

	public static void readFile(String filePath) throws FileNotFoundException, DukeException {
		File file = new File(filePath);
		Scanner scan = new Scanner(file);

		while(scan.hasNext()) {
			extractData(scan.nextLine());
		}
	}

	private static void extractData(String line) throws DukeException {
		try {
			String[] args = line.split("\\|");
			String taskType = args[0].trim();
			boolean isDone = Integer.parseInt(args[1].trim()) == 1;
			String description = args[2].trim();

			switch (taskType) {
			case "T":
				TodoCommand.addTodo(description, isDone, false);
				break;
			case "D":
				description = description + " /by " + args[3].trim();
				DeadlineCommand.addDeadline(description, isDone, false);
				break;
			case "E":
				description = description + " /at " + args[3].trim();
				EventCommand.addEvent(description, isDone, false);
				break;
			default:
				TextUi.printError("Cannot read task!");
				break;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			TextUi.printError("Error reading file!");
		}
	}
}
