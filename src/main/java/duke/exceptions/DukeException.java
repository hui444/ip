package duke.exceptions;

public class DukeException extends Exception {

    public String errorMessage;

    public DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
