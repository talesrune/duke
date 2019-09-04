//import java.io.*;
import java.io.IOException;
//import java.util.*;

public class Duke {
    /**Hi.*/ //1. indentation 2. must have full-stop 3. special commenting
    private Storage storage;
    private TaskList items;
    private Ui ui;

    public Duke() {
    }

    /**Hi.*/
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            items = new TaskList(storage.read());
        } catch (IOException e) {
            ui.showLoadingError();
            items = new TaskList();
        }
    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**Hi.*/
    public void run() {
        ui.showWelcome();
        String sentence;

        while (true) {
            sentence = ui.input();
            ui.showLine();
            String output = "";
            output = Parser.parse(sentence, ui, items);
            try {
                if (output.equals("bye")) {
                    ui.showBye();
                    storage.write(items);
                    break;
                } else if (output.isEmpty()) {
                    throw new DukeException("     ☹ OoPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                ui.showErrorMsg(e.getMessage());
            } catch (Exception e) {
                ui.showErrorMsg("     New error, please fix:");
                e.printStackTrace();
                ui.showErrorMsg("     Duke will continue as per normal.");
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}