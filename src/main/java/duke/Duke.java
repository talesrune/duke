package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.dukeexception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Represents a duke that controls the program.
 */
public class Duke {

    private Storage storage;
    private TaskList items;
    private Ui ui;

    /**
     * Creates a duke to initialize storage, task list, and ui.
     *
     * @param filePath The location of the text file.
     */
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

    /**
     * Echoes the user input back the the user.
     * (Not in use)
     *
     * @param input The user input.
     * @return String of the response.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Retrieves a command by interpreting the user input (GUI).
     *
     * @param sentence The user input.
     * @return Command to be executed thereafter.
     */
    public Command getCommand(String sentence) throws Exception {
        Command cmd = Parser.parse(sentence, items);
        return cmd;
    }

    /**
     * Executes a command to overwrite exiting storage with an updated task list (GUI).
     *
     * @param cmd Command to be executed.
     * @throws Exception  If there is an error writing the text file.
     */
    public void saveState(Command cmd) throws IOException {
        cmd.executeStorage(items,ui,storage);
    }

    /**
     * Executes a command and outputs the result to the user (GUI).
     *
     * @param cmd Command to be executed.
     * @return String to be outputted.
     */
    public String executeCommand(Command cmd) {
        String str = cmd.executeGui(items, ui);
        return str;
    }

    /**
     * Runs the duke program until exit command is executed.
     */
    public void run() {
        ui.showWelcome();
        String sentence;

        while (true) {
            sentence = ui.readCommand();
            ui.showLine();
            try {
                Command cmd = Parser.parse(sentence, items);
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