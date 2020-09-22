package duke.tasks;

public class Deadline extends Task{

    protected String deadlineDate;

    public Deadline(String description, String deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineDate + ")";
    }

    @Override
    public String fileString() {
        int val = isDone ? 1 : 0;
        return "D | " + val + " | " + this.description + " | " + this.deadlineDate;
    }
}
