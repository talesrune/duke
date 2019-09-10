package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public abstract class Command {

    public abstract void execute(TaskList items, Ui ui);

    public abstract String executeGui(TaskList items, Ui ui);

    public abstract void executeStorage(TaskList items, Ui ui, Storage storage) throws IOException;
}

