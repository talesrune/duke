//import java.io.*;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList; //to use arraylist
//import java.util.*;

public class Duke {
    /**Hi.*/ //1. indentation 2. must have full-stop 3. special commenting
    public static void main(String[] args) throws IOException {
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
        //Todo t = new Todo("read book");
        //Deadline u = new Deadline("return book", "6 June");
        //t.setStatusIcon(true);
        //Event v = new Event("buy bread", "7.40pm today");

        ArrayList<Task> items = new ArrayList<Task>();
        //items.add(t);
        //items.add(u);
        //items.add(v);

        //Reading file
        File file = new File("C:\\repos\\duke_new2\\data\\duke.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        String taskDesc = "";
        String dateDesc = "";
        while ((st = br.readLine()) != null) {
            String[] commandList = st.split("\\|");
            try {
                for (int i = 0; i < commandList.length; i++) {
                    if (i == 2) {
                        taskDesc = commandList[i];
                    } else if (i == 3) {
                        dateDesc = commandList[i];
                    }
                }
                Boolean checked = false;
                if (commandList.length > 1) {
                    if (!(commandList[1].equals("1") || commandList[1].equals("0"))) {
                        throw new DukeException("Error reading 1 or 0, skipping to next line");
                    }
                    checked = commandList[1].equals("1");
                }
                if (commandList[0].equals("T")) {
                    Todo t = new Todo(taskDesc);
                    t.setStatusIcon(checked);
                    items.add(t);
                } else if (commandList[0].equals("D")) {
                    Deadline u = new Deadline(taskDesc, dateDesc);
                    u.setStatusIcon(checked);
                    if (!taskDesc.isEmpty() && !dateDesc.isEmpty()) {
                        items.add(u);
                    } else {
                        throw new DukeException("Error reading description or date/time, skipping to next line");
                    }
                } else if (commandList[0].equals("E")) {
                    Event v = new Event(taskDesc, dateDesc);
                    v.setStatusIcon(checked);
                    items.add(v);
                } else if (!commandList[0].isEmpty()) {
                    throw new DukeException("Error reading whether if its T, D, or E, skipping to next line");
                }
            } catch (Exception e) {
                System.out.println("     Error when reading current line, please fix the text file:");
                e.printStackTrace();
                System.out.println("     Duke will continue reading the rest of file");
            }
        }
        br.close();

        while (true) {
            word = input.nextLine();
            String[] arr = word.split(" ");
            System.out.println(line);
            Todo todoObj; //temp object
            Deadline deadlineObj;
            Event eventObj;
            taskDesc = "";
            dateDesc = "";
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
                } else if (arr.length > 0 && arr[0].equals("find")) {
                    if (arr.length == 1) {
                        throw new DukeException("     ☹ OOPS!!! The keyword cannot be empty.");
                    } else {
                        if (arr[1].trim().isEmpty()) {
                            throw new DukeException("     ☹ OOPS!!! The keyword cannot be empty.");
                        } else {
                            System.out.println("     Here are the matching tasks in your list:");
                            int numFound = 0;
                            for (int i = 0; i < items.size(); i++) {
                                if (items.get(i).getDescription().contains(arr[1])) {
                                    System.out.println("     " + (i + 1) + "." + items.get(i).toString());
                                    numFound++;
                                }
                            }
                            if (numFound == 0) {
                                System.out.println("     No matching tasks found.");
                            }
                        }
                    }
                } else if (arr.length > 0 && arr[0].equals("todo")) {
                    for (int i = 1; i < arr.length; i++) {
                        taskDesc += arr[i] + " ";
                    }
                    taskDesc = taskDesc.trim();
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

                    //Writing it to file
                    String fileContent = "";
                    for (int i = 0; i < items.size(); i++) {
                        fileContent += items.get(i).toFile() + "\n";
                    }
                    BufferedWriter writer = new BufferedWriter(new FileWriter("C:/repos/duke_new2/data/duke.txt"));
                    writer.write(fileContent);
                    writer.close();
                    break;
                } else {
                    throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("     New error, please fix:");
                e.printStackTrace();
                System.out.println("     Duke will continue as per normal.");
            }
            System.out.println(line);
        }
    }
}