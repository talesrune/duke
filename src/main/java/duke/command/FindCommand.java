package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    protected String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList items, Ui ui) {
        ui.showFind(items, keyword);
    }

    @Override
    public String executeGui(TaskList items, Ui ui) {
        String str = ui.showFindGui(items, keyword);
        return str;
    }

    @Override
    public void executeStorage(TaskList items, Ui ui, Storage storage) {

    }
}