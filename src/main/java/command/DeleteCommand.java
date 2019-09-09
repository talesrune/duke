package command;


import storage.Storage;
import ui.Ui;
import task.TaskList;

public class DeleteCommand extends Command {
    protected int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList items, Ui ui) {
        String deletedStr = "       " + items.get(index).toString();
        items.remove(index);
        ui.showDelete(items, deletedStr);
    }

    @Override
    public void executeStorage(TaskList items, Ui ui, Storage storage) {

    }
}
