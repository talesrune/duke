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

    //public void setDescription(String description) {
    //}

    public String getStatusIcon() {
        return (isDone ? "[✓]" : "[✗]"); //return tick or X symbols
    }

    public void setStatusIcon(boolean setDone) {
        isDone = setDone;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }

}