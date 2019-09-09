package command;

import storage.Storage;
import ui.Ui;
import task.TaskList;

public class DoneCommand extends Command {
    protected final int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList items, Ui ui) {
        items.get(index).setStatusIcon(true);
        ui.showDone(items, index);
    }

    @Override
    public void executeStorage(TaskList items, Ui ui, Storage storage) {

    }
}
