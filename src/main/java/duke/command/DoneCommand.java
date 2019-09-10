package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

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
    public String executeGui(TaskList items, Ui ui) {
        items.get(index).setStatusIcon(true);
        String str = ui.showDoneGui(items, index);
        return str;
    }

    @Override
    public void executeStorage(TaskList items, Ui ui, Storage storage) {

    }
}
