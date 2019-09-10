package duke.command;


import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

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
    public String executeGui(TaskList items, Ui ui) {
        String deletedStr = "       " + items.get(index).toStringGui();
        items.remove(index);
        String str = ui.showDeleteGui(items, deletedStr);
        return str;
    }

    @Override
    public void executeStorage(TaskList items, Ui ui, Storage storage) {

    }
}
