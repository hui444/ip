package duke;

import duke.common.Commands;
import duke.parser.Parser;
import duke.storage.StorageFile;
import duke.ui.TextUi;

import java.util.Scanner;

public class Duke {

    private TextUi ui;
    private StorageFile storage;
    private TaskList task;

    public static void main(String[] args) {
        new Duke().run(args);
    }

    /** Runs the program until termination. */
    public void run(String[] args) {
        start(args);
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Sets up the required object, loads up the data from the storage file,
     * and prints greeting message.
     *
     * @param args Arguments supplied by the user at Duke launch.
     */
    private void start(String[] args) {
        try {
            ui = new TextUi();
            task = new TaskList();
            storage = new StorageFile("data/duke.txt", "data");

            System.out.println(TextUi.MESSAGE_GREET_SIGN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Prints bye message and exits Duke. */
    private void exit() {
        System.out.println(TextUi.MESSAGE_BYE_SIGN);
        System.exit(0);
    }

    /** Reads the user command and executes it, until the user issues the exit command. */
    private static void runCommandLoopUntilExitCommand() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while(!line.equals(Commands.COMMAND_BYE)) {
            Parser.handleTasks(line);
            in = new Scanner(System.in);
            line = in.nextLine();
        }
    }
}
