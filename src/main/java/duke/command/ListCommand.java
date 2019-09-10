package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList items, Ui ui) {
        ui.showTaskList(items);
    }

    @Override
    public String executeGui(TaskList items, Ui ui) {
        String str = ui.showTaskListGui(items);
        return str;
    }

    @Override
    public void executeStorage(TaskList items, Ui ui, Storage storage) {
    }
}