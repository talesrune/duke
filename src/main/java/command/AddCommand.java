package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class AddCommand extends Command {
    protected Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList items, Ui ui) {
        items.add(task);
        ui.showAdd(items);
    }

    @Override
    public void executeStorage(TaskList items, Ui ui, Storage storage) {

    }
}