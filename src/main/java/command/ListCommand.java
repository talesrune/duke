package command;

import storage.Storage;
import ui.Ui;
import task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList items, Ui ui) {
        ui.showTaskList(items);
    }

    @Override
    public void executeStorage(TaskList items, Ui ui, Storage storage) {

    }
}