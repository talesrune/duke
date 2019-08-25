import java.util.Scanner;

public class Duke {
    /**Hi.*/ //1. indentation 2. must have full-stop 3. special commenting
    public static void main(String[] args) {
        String line = "    ____________________________________________________________";
        System.out.println(line);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(line);
        Scanner input = new Scanner(System.in);
        String word;
        String[] items = new String[100];
        int count = 0;
        while (true) {
            word = input.nextLine();
            System.out.println(line);
            if (word.equals("list")) {
                for (int i = 0; i < count; i++) {
                    System.out.println("     " + (i + 1) + ". " + items[i]);
                }

            } else if (!word.equals("bye")) {
                items[count] = word;
                count++;
                System.out.println("     added: " + word);
            } else {
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }
            System.out.println(line);
        }
    }
}