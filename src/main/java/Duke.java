import java.util.Scanner;

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
        String[] items = new String[100];
        boolean[] checkitem = new boolean[100];
        int count = 0;
        //test tasks
        //items[0] = "read book";
        //items[1] = "return book";
        //items[2] = "buy bread";
        //count = 3;
        //test tasks end

        while (true) {
            word = input.nextLine();
            String[] arr = word.split(" ");
            System.out.println(line);
            if (word.equals("list")) {
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    String tc;
                    if (!checkitem[i]) {
                        tc = cross;
                    } else {
                        tc = tick;
                    }
                    System.out.println("     " + (i + 1) + "." + tc  + " " + items[i]);
                }

            } else if (arr[0].equals("done")) {
                String tc = tick;
                checkitem[Integer.parseInt(arr[1]) - 1] = true;
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       " + tc + " " + items[Integer.parseInt(arr[1]) - 1]);

            } else if (!word.equals("bye") && !word.isEmpty()) {
                items[count] = word;
                count++;
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