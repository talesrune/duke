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

        //Test tasks
        Todo t = new Todo("read book");
        Deadline u = new Deadline("return book", "6 June");
        t.setStatusIcon(true);
        Event v = new Event("buy bread", "7.40pm today");

        ArrayList<Task> items = new ArrayList<Task>();
        items.add(t);
        items.add(u);
        items.add(v);

        while (true) {
            word = input.nextLine();
            String[] arr = word.split(" ");
            System.out.println(line);
            Todo todo_obj; //temp object
            Deadline deadline_obj;
            Event event_obj;
            String task_desc = "";
            String date_desc = "";
            boolean getDate = false;

            if (word.equals("list")) {
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < items.size(); i++) {
                    System.out.println("     " + (i + 1) + "." + items.get(i).toString());
                }
            } else if (arr[0].equals("done")) {
                int tasknum = Integer.parseInt(arr[1]) - 1;
                items.get(tasknum).setStatusIcon(true);
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       " + items.get(tasknum).toString());
            } else if (arr[0].equals("todo")) {
                for (int i = 1; i < arr.length; i++)
                {
                   task_desc += arr[i] + " ";
                }
                todo_obj = new Todo(task_desc);
                items.add(todo_obj);
                System.out.println("     Got it. I've added this task:");
                System.out.println("       " + items.get(items.size()-1).toString());
                System.out.println("     Now you have " + items.size() + " tasks in the list.");
            } else if (arr[0].equals("deadline") || arr[0].equals("event")) {
                for (int i = 1; i < arr.length; i++) {
                    if (!arr[i].substring(0,1).equals("/") && !getDate) {
                        task_desc += arr[i] + " ";
                    } else {
                        if (!getDate) {//detect "/"
                            getDate = true;
                        } else {
                            date_desc += arr[i] + " ";
                        }
                    }
                }
                task_desc = task_desc.trim();
                date_desc = date_desc.trim();
                if (arr[0].equals("deadline")) {
                    deadline_obj = new Deadline(task_desc, date_desc);
                    items.add(deadline_obj);
                } else {
                    event_obj = new Event(task_desc, date_desc);
                    items.add(event_obj);
                }
                System.out.println("     Got it. I've added this task:");
                System.out.println("       " + items.get(items.size()-1).toString());
                System.out.println("     Now you have " + items.size() + " tasks in the list.");
            }
            else if (word.equals("bye")) {
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }
            System.out.println(line);
        }
    }
}