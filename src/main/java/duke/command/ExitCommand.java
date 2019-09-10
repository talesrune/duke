package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;
import java.io.IOException;


public class ExitCommand extends Command {

    @Override
    public  void execute(TaskList items, Ui ui) {
    }

    @Override
    public  String executeGui(TaskList items, Ui ui) {
        return "";
    }

    @Override
    public void executeStorage(TaskList items, Ui ui, Storage storage) throws IOException {
        ui.showBye();
        storage.write(items);
    }
}