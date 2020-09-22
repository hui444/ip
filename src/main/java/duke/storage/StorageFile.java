package duke.storage;

import duke.exceptions.DukeException;
import duke.ui.TextUi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Initialises file for storage
 */
public class StorageFile {
    public StorageFile(String filePath, String directoryName) {
        File file = new File(filePath);
        File directory = new File(directoryName);

        try {
            directory.mkdir();
            file.createNewFile();
            Storage.readFile(filePath);
        } catch (FileNotFoundException e) {
            TextUi.printError("File not found");
        } catch (IOException e) {
            TextUi.printError(e.getMessage());
        } catch (DukeException e) {
            TextUi.printError(e.errorMessage);
        }
    }
}
