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
        return getStatusIcon() + " " + description;
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