import java.util.Scanner;
import java.util.ArrayList; //to use arraylist
//import java.util.*;

public class Duke {
    /**Hi.*/ //1. indentation 2. must have full-stop 3. special commenting
    public static void main(String[] args) {
        String line = "    ____________________________________________________________";
        String tick = "[✓]";
        String cross = "[✗]";
        System.out.println(line);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(line);
        Scanner input = new Scanner(System.in);
        String word;

        Task t = new Task("read book");
        Task u = new Task("return book");
        t.setStatusIcon(true);
        Task v = new Task("buy bread");

        ArrayList<Task> items = new ArrayList<Task>();
        items.add(t);
        items.add(u);
        items.add(v);

        while (true) {
            word = input.nextLine();
            String[] arr = word.split(" ");
            System.out.println(line);
            Task temp; //temp object

            if (word.equals("list")) {
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < items.size(); i++) {
                    System.out.println("     " + (i + 1) + "." + items.get(i).getStatusIcon()
                            + " " + items.get(i).getDescription());
                }
            } else if (arr[0].equals("done")) {
                int tasknum = Integer.parseInt(arr[1]) - 1;
                items.get(tasknum).setStatusIcon(true);
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       " + tick + " " + items.get(tasknum).getDescription());
            } else if (!word.equals("bye") && !word.isEmpty()) {
                temp = new Task(word);
                items.add(temp);
                System.out.println("     added: " + word);
            } else if (word.equals("bye")) {
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }
            System.out.println(line);
        }
    }
}