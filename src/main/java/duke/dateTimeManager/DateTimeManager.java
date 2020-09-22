package duke.dateTimeManager;

import duke.exceptions.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeManager {
    public static String dateTimeManager(String dateTime) throws DukeException {
        if(dateTime.trim().split(" ")[0].contains("-")) {
            String formattedDateTime;
            try {
                String[] splitData = dateTime.trim().split(" ");
                String date = splitData[0];

                LocalDate formattedDate = LocalDate.parse(date);
                formattedDateTime = formattedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

                if (splitData.length > 1) {
                    formattedDateTime += ",";
                    for(int i = 1; i < splitData.length; i++) {
                        formattedDateTime += " " + splitData[i];
                    }
                }

                return String.valueOf(formattedDateTime);
            } catch (DateTimeException e) {
                throw new DukeException(e.getMessage());
            }
        } else {
            return dateTime;
        }
    }
}
