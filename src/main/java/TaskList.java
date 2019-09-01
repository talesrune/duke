import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> items;

    public TaskList() {
        items = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> items) {
        this.items = items;
    }

    public void add(Task taskObj) {
        items.add(taskObj);
    }

    public void remove(int index) {
        items.remove(index);
    }

    public int size() {
        return items.size();
    }

    public Task get(int index) {
        return items.get(index);
    }

    /**Hi.*/
    public String getList() {
        String listStr = "";
        for (int i = 0; i < items.size(); i++) {
            listStr += "     " + (i + 1) + "." + items.get(i).toString() + "\n";
        }
        return listStr;
    }
}
