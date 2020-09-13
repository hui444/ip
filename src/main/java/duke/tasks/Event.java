package duke.tasks;

public class Event extends Task {
	protected String eventDate;

	public Event(String description, String eventDate) {
	    super(description);
	    this.eventDate = eventDate;
	}

	@Override
	public String toString() {
	    return "[E]" + super.toString() + "(at:" + eventDate + ")";
	}

	@Override
	public String fileString() {
		int val = isDone ? 1 : 0;
		return "E | " + val + " | " + this.description + "|" + this.eventDate;
	}
}
