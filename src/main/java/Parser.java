public class Parser {

    /**Hi.*/
    public static String parse(String sentence, Ui ui, TaskList items) {
        String[] arr = sentence.split(" ");
        String taskDesc = "";
        String dateDesc = "";
        boolean getDate = false;
        try {
            if (sentence.equals("list")) {
                ui.showTaskList(items);
                return "list";
            } else if (arr.length > 0 && (arr[0].equals("done") || arr[0].equals("delete"))) {
                if (arr.length == 1) {
                    throw new DukeException("     ☹ OOPS!!! The task number cannot be empty.");
                } else {
                    int tasknum = Integer.parseInt(arr[1]) - 1;
                    if (tasknum < 0 || tasknum >= items.size()) {
                        throw new DukeException("     ☹ OOPS!!! Invalid task number.");
                    } else {
                        if (arr[0].equals("done")) {
                            items.get(tasknum).setStatusIcon(true);
                            ui.showDone(items, tasknum);
                            return "done";
                        } else { //delete
                            String deletedStr = "       " + items.get(tasknum).toString();
                            items.remove(tasknum);
                            ui.showDelete(items, deletedStr);
                            return "delete";
                        }
                    }
                }
            } else if (arr.length > 0 && arr[0].equals("find")) {
                if (arr.length == 1) {
                    throw new DukeException("     ☹ OOPS!!! The keyword cannot be empty.");
                } else {
                    if (arr[1].trim().isEmpty()) {
                        throw new DukeException("     ☹ OOPS!!! The keyword cannot be empty.");
                    } else {
                        ui.showFind(items, arr[1]);
                        return "find";
                    }
                }
            } else if (arr.length > 0 && arr[0].equals("todo")) {
                for (int i = 1; i < arr.length; i++) {
                    taskDesc += arr[i] + " ";
                }
                taskDesc = taskDesc.trim();
                if (taskDesc.isEmpty()) {
                    throw new DukeException("     ☹ OOPS!!! The description of a todo cannot be empty.");
                } else {
                    Task taskObj = new Todo(taskDesc);
                    items.add(taskObj);
                    ui.showAdd(items);
                    return "todo";
                }
            } else if (arr.length > 0 && (arr[0].equals("deadline") || arr[0].equals("event"))) {
                for (int i = 1; i < arr.length; i++) {
                    if ((arr[i].trim().isEmpty() || !arr[i].substring(0, 1).equals("/")) && !getDate) {
                        taskDesc += arr[i] + " ";
                    } else {
                        if (!getDate) { //detect "/"
                            getDate = true;
                        } else {
                            dateDesc += arr[i] + " ";
                        }
                    }
                }
                taskDesc = taskDesc.trim();
                dateDesc = dateDesc.trim();
                if (taskDesc.isEmpty()) {
                    throw new DukeException("     ☹ OOPS!!! The description of a " + arr[0] + " cannot be empty.");
                } else if (dateDesc.isEmpty()) {
                    throw new DukeException("     ☹ OOPS!!! The description of date/time for "
                            + arr[0] + " cannot be empty.");
                } else {
                    Task taskObj;
                    if (arr[0].equals("deadline")) {
                        taskObj = new Deadline(taskDesc, dateDesc);
                        items.add(taskObj);
                    } else {
                        taskObj = new Event(taskDesc, dateDesc);
                        items.add(taskObj);
                    }
                    ui.showAdd(items);
                    return "deadline/event";
                }
            } else if (sentence.equals("bye")) {
                return "bye";
            } else {
                return "";
            }
        } catch (DukeException e) {
            ui.showErrorMsg(e.getMessage());
        } catch (Exception e) {
            ui.showErrorMsg("     New error, please fix:");
            e.printStackTrace();
            ui.showErrorMsg("     Duke will continue as per normal.");
        }
        return "error";
    }
}
