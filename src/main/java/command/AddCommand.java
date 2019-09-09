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
    public String executeGui(TaskList items, Ui ui) {
        items.add(task);
        String str = ui.showAddGui(items);
        return str;
    }

    @Override
    public void executeStorage(TaskList items, Ui ui, Storage storage) {

    }
}