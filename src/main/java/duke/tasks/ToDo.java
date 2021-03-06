package duke.tasks;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String fileString() {
        int val = isDone ? 1 : 0;
        return "T | " + val + " | " + this.description;
    }
}
