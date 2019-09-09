package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

public abstract class Command {

    public abstract void execute(TaskList items, Ui ui);

    public abstract void executeStorage(TaskList items, Ui ui, Storage storage) throws IOException;
}

