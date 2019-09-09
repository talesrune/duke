package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

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
    public void executeStorage(TaskList items, Ui ui, Storage storage) {

    }
}