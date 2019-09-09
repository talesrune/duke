//import java.io.*;
import command.Command;
import command.ExitCommand;
import dukeexception.DukeException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

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
            sentence = ui.readCommand();
            ui.showLine();
            try {
                Command cmd = Parser.parse(sentence, ui,items);
                if (cmd instanceof ExitCommand) {
                    cmd.executeStorage(items,ui,storage);
                    break;
                } else {
                    cmd.execute(items,ui);
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