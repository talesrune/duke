package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "[✓]" : "[✗]"); //return [✓] : [✗] symbols
    }

    public String getStatusIconGui() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return [✓]" : "[✗] symbols
    }

    public void setStatusIcon(boolean setDone) {
        isDone = setDone;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    public String toStringGui() {
        return getStatusIconGui() + " " + description;
    }

    /**Hi.*/
    public String toFile() {
        String numStr = "";
        if (isDone) {
            numStr = "1|";
        } else {
            numStr = "0|";
        }
        return  numStr + description;
    }
}