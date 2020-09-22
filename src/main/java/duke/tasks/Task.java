package duke.tasks;

import java.util.Collection;

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

	public String fileString() {
		int val = isDone ? 1 : 0;
		return " | " + val + " | " + this.description;
	}

	public String getDescription() {
		return this.description;
	}
}
