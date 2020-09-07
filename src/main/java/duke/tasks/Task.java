package duke.tasks;

public class Task {
	public String description;
	protected Boolean isDone;

	public Task(String description) {
	    this.description = description;
	    this.isDone = false;
	}

	public String getStatusIcon() {
		//returns [tick] or [X] symbols
	    return (isDone ? "[" + "\u2713" + "]" : "[" + "\u2718" + "]");
	}

	public void markAsDone() {
	    this.isDone = true;
	}

	@Override
	public String toString() {
	    return getStatusIcon() + " " + this.description;
	}
}
