package duke.ui;

import java.io.InputStream;
import java.util.Scanner;
import java.io.PrintStream;
import duke.task.TaskList;

public class Ui {

    protected static final String line = "    ____________________________________________________________";
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

    public static String showLineGui() {
        return line + "\n";
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void showTaskList(TaskList items) {
        out.println("     Here are the tasks in your list:");
        out.print(items.getList());
    }

    public static String showTaskListGui(TaskList items) {
        String str = "     Here are the tasks in your list:\n" + items.getListGui();
        return str;
    }

    public void showDone(TaskList items, int index) {
        out.println("     Nice! I've marked this task as done:");
        out.println("       " + items.get(index).toString());
    }

    public static String showDoneGui(TaskList items, int index) {
        String str = "     Nice! I've marked this task as done:\n       " + items.get(index).toStringGui() + "\n";
        return str;
    }

    /**Hi.*/
    public void showDelete(TaskList items, String deletedTask) {
        out.println("     Noted. I've removed this task:");
        out.println(deletedTask);
        out.println("     Now you have " + items.size() + " tasks in the list.");
    }

    /**Hi.*/
    public static String showDeleteGui(TaskList items, String deletedTask) {
        String str = "     Noted. I've removed this task:\n" + deletedTask
                + "\n     Now you have " + items.size() + " tasks in the list.\n";
        return str;
    }

    /**Hi.*/
    public void showAdd(TaskList items) {
        out.println("     Got it. I've added this task:");
        out.println("       " + items.get(items.size() - 1).toString());
        out.println("     Now you have " + items.size() + " tasks in the list.");
    }

    /**Hi.*/
    public static String showAddGui(TaskList items) {
        String str = "     Got it. I've added this task:\n       "
                + items.get(items.size() - 1).toString() + "\n     Now you have "
                + items.size() + " tasks in the list.\n";
        return str;
    }

    /**Hi.*/
    public void showWelcome() {
        showLine();
        out.println("     Hello! I'm Duke");
        out.println("     What can I do for you?");
        showLine();
    }

    /**Gui.*/
    public static String showWelcomeGui() {
        String str = line + "\n     Hello! I'm Duke\n     What can I do for you?\n" + line;
        return str;
    }

    public void showBye() {
        out.println("     Bye. Hope to see you again soon!");
    }

    public static String showByeGui() {
        String str = "     Bye. Hope to see you again soon!\n";
        return str;
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

    /**Hi.*/
    public static String showFindGui(TaskList items, String keyword) {
        String str = "     Here are the matching tasks in your list:\n";
        int numFound = 0;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getDescription().contains(keyword)) {
                str += "     " + (i + 1) + "." + items.get(i).toString() + "\n";
                numFound++;
            }
        }
        if (numFound == 0) {
            str += "     No matching tasks found.\n";
        }
        return str;
    }

    public void showLoadingError() {
        out.println("File not found, creating an empty list");
    }

    public static String showLoadingErrorGui() {
        String str = "File not found, creating an empty list\n";
        return str;
    }

    public void showErrorMsg(String message) {
        out.println(message);
    }

    public static String showErrorMsgGui(String message) {
        return message + "\n";
    }
}
