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
            Todo todoObj; //temp object
            Deadline deadlineObj;
            Event eventObj;
            String taskDesc = "";
            String dateDesc = "";
            boolean getDate = false;
            try {
                if (word.equals("list")) {
                    System.out.println("     Here are the tasks in your list:");
                    for (int i = 0; i < items.size(); i++) {
                        System.out.println("     " + (i + 1) + "." + items.get(i).toString());
                    }
                } else if (arr.length > 0 && arr[0].equals("done")) {
                    if (arr.length == 1) {
                        throw new DukeException("     ☹ OOPS!!! The task number cannot be empty.");
                    } else {
                        int tasknum = Integer.parseInt(arr[1]) - 1;
                        if (tasknum < 0 || tasknum >= items.size()) {
                            throw new DukeException("     ☹ OOPS!!! Invalid task number.");
                        } else {
                            items.get(tasknum).setStatusIcon(true);
                            System.out.println("     Nice! I've marked this task as done:");
                            System.out.println("       " + items.get(tasknum).toString());
                        }
                    }
                } else if (arr.length > 0 && arr[0].equals("todo")) {
                    for (int i = 1; i < arr.length; i++) {
                        taskDesc += arr[i] + " ";
                    }
                    if (taskDesc.isEmpty()) {
                        throw new DukeException("     ☹ OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        todoObj = new Todo(taskDesc);
                        items.add(todoObj);
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + items.get(items.size() - 1).toString());
                        System.out.println("     Now you have " + items.size() + " tasks in the list.");
                    }
                } else if (arr.length > 0 && (arr[0].equals("deadline") || arr[0].equals("event"))) {
                    for (int i = 1; i < arr.length; i++) {
                        if ((arr[i].trim().isEmpty() || !arr[i].substring(0, 1).equals("/")) && !getDate) {
                            taskDesc += arr[i] + " ";
                        } else {
                            if (!getDate) { //detect "/"
                                getDate = true;
                            } else {

                                dateDesc += arr[i] + " ";
                            }
                        }
                    }
                    taskDesc = taskDesc.trim();
                    dateDesc = dateDesc.trim();
                    if (taskDesc.isEmpty()) {
                        throw new DukeException("     ☹ OOPS!!! The description of a " + arr[0] + " cannot be empty.");
                    } else if (dateDesc.isEmpty()) {
                        throw new DukeException("     ☹ OOPS!!! The description of date/time for "
                                + arr[0] + " cannot be empty.");
                    } else {
                        if (arr[0].equals("deadline")) {
                            deadlineObj = new Deadline(taskDesc, dateDesc);
                            items.add(deadlineObj);
                        } else {
                            eventObj = new Event(taskDesc, dateDesc);
                            items.add(eventObj);
                        }
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + items.get(items.size() - 1).toString());
                        System.out.println("     Now you have " + items.size() + " tasks in the list.");
                    }
                } else if (word.equals("bye")) {
                    System.out.println("     Bye. Hope to see you again soon!");
                    System.out.println(line);
                    break;
                } else {
                    throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("New error, please fix:");
                e.printStackTrace();
                System.out.println("Duke will continue as per normal.");
            }
            System.out.println(line);
        }
    }
}