package command;

import storage.Storage;
import ui.Ui;
import task.TaskList;
import java.io.IOException;


public class ExitCommand extends Command {

    @Override
    public  void execute(TaskList items, Ui ui) {

    }

    @Override
    public void executeStorage(TaskList items, Ui ui, Storage storage) throws IOException {
        ui.showBye();
        storage.write(items);
    }
}