package duke.task;

import java.util.ArrayList;

/**
 * Represents a task list that stores a list of tasks.
 */
public class TaskList {
    protected ArrayList<Task> items;

    /**
     * Creates an empty task list using an array list.
     */
    public TaskList() {
        items = new ArrayList<Task>();
    }

    /**
     * Creates an updated task list with the specified array list.
     *
     * @param items The updated array list.
     */
    public TaskList(ArrayList<Task> items) {
        this.items = items;
    }

    /**
     * To add a task into the task list.
     *
     * @param taskObj a task to be added.
     */
    public void add(Task taskObj) {
        items.add(taskObj);
    }

    /**
     * To remove a task from the task list.
     *
     * @param index The index of task to be removed.
     */
    public void remove(int index) {
        items.remove(index);
    }

    /**
     * The size of the task list.
     *
     * @return int that represents the task list size.
     */
    public int size() {
        return items.size();
    }

    /**
     * Retrieves the task from the task list via the task's index.
     *
     * @param index The index of task.
     * @return Task which is retrieved from the index.
     */
    public Task get(int index) {
        return items.get(index);
    }

    /**
     * Retrieves all tasks from the task list.
     *
     * @return String that contains the whole list of tasks.
     */
    public String getList() {
        String listStr = "";
        for (int i = 0; i < items.size(); i++) {
            listStr += "     " + (i + 1) + "." + items.get(i).toString() + "\n";
        }
        return listStr;
    }

    /**
     * Retrieves all tasks from the task list (GUI).
     *
     * @return String that contains the whole list of tasks.
     */
    public String getListGui() {
        String listStr = "";
        for (int i = 0; i < items.size(); i++) {
            listStr += "     " + (i + 1) + "." + items.get(i).toStringGui() + "\n";
        }
        return listStr;
    }
}
