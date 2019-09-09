package task;

import task.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Deadline extends Task {
    protected Date by;
    protected String[] suf = { "st", "nd", "rd", "th" };
    protected SimpleDateFormat datetimeFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**Hi.*/
    public Deadline(String description, String by) throws ParseException {
        super(description);
        Date dateTime = null;
        try {
            dateTime = datetimeFormat.parse(by);
            this.by = dateTime;
        } catch (ParseException e) {
            System.out.println("Error reading date/time, please use this format \"d/MM/yyyy HHmm\"");
            throw e;
        }

    }

    @Override
    public String toString() {
        SimpleDateFormat datetimeFormat2 = new SimpleDateFormat("MMMMM yyyy, h:mm a");
        SimpleDateFormat datetimeFormat3 = new SimpleDateFormat("MMMMM yyyy, ha");
        String displayDT = "";

        int day = Integer.parseInt(new SimpleDateFormat("d").format(by));
        int min = Integer.parseInt(new SimpleDateFormat("m").format(by));
        if (min > 0) {
            displayDT = datetimeFormat2.format(by);
        } else {
            displayDT = datetimeFormat3.format(by);
        }
        int sufIndex = -1;

        if (day == 1 || day == 21 || day == 31) {
            sufIndex = 0;
        } else if (day == 2 || day == 22) {
            sufIndex = 1;
        } else if (day == 3 || day == 23) {
            sufIndex = 2;
        } else if (day > 3 && day < 31) {
            sufIndex = 3;
        }
        String suffixStr = day + suf[sufIndex];
        displayDT = suffixStr + " of " + displayDT;
        return "[D]" + super.toString() + " (by: " + displayDT + ")";
    }

    @Override
    public String toStringGui() {
        SimpleDateFormat datetimeFormat2 = new SimpleDateFormat("MMMMM yyyy, h:mm a");
        SimpleDateFormat datetimeFormat3 = new SimpleDateFormat("MMMMM yyyy, ha");
        String displayDT = "";

        int day = Integer.parseInt(new SimpleDateFormat("d").format(by));
        int min = Integer.parseInt(new SimpleDateFormat("m").format(by));
        if (min > 0) {
            displayDT = datetimeFormat2.format(by);
        } else {
            displayDT = datetimeFormat3.format(by);
        }
        int sufIndex = -1;

        if (day == 1 || day == 21 || day == 31) {
            sufIndex = 0;
        } else if (day == 2 || day == 22) {
            sufIndex = 1;
        } else if (day == 3 || day == 23) {
            sufIndex = 2;
        } else if (day > 3 && day < 31) {
            sufIndex = 3;
        }
        String suffixStr = day + suf[sufIndex];
        displayDT = suffixStr + " of " + displayDT;
        return "[D]" + super.toStringGui() + " (by: " + displayDT + ")";
    }

    @Override
    public String toFile() {
        String datetimeStr = datetimeFormat.format(by);
        return "D|" + super.toFile() + "|" + datetimeStr;
    }
}