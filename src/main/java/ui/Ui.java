package ui;

import java.io.InputStream;
import java.util.Scanner;
import java.io.PrintStream;
import task.TaskList;

public class Ui {

    protected final String line = "    ____________________________________________________________";
    protected final Scanner in;
    protected final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showLine() {
        out.println(line);
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void showTaskList(TaskList items) {
        out.println("     Here are the tasks in your list:");
        out.print(items.getList());
    }

    public void showDone(TaskList items, int index) {
        out.println("     Nice! I've marked this task as done:");
        out.println("       " + items.get(index).toString());
    }

    /**Hi.*/
    public void showDelete(TaskList items, String deletedTask) {
        out.println("     Noted. I've removed this task:");
        out.println(deletedTask);
        out.println("     Now you have " + items.size() + " tasks in the list.");
    }

    /**Hi.*/
    public void showAdd(TaskList items) {
        out.println("     Got it. I've added this task:");
        out.println("       " + items.get(items.size() - 1).toString());
        out.println("     Now you have " + items.size() + " tasks in the list.");
    }

    /**Hi.*/
    public void showWelcome() {
        showLine();
        out.println("     Hello! I'm Duke");
        out.println("     What can I do for you?");
        showLine();
    }

    public void showBye() {
        out.println("     Bye. Hope to see you again soon!");
    }

    /**Hi.*/
    public void showFind(TaskList items, String keyword) {
        out.println("     Here are the matching tasks in your list:");
        int numFound = 0;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getDescription().contains(keyword)) {
                out.println("     " + (i + 1) + "." + items.get(i).toString());
                numFound++;
            }
        }
        if (numFound == 0) {
            out.println("     No matching tasks found.");
        }
    }

    public void showLoadingError() {
        out.println("File not found, creating an empty list");
    }

    public void showErrorMsg(String message) {
        out.println(message);
    }
}
