package storage;

import task.TaskList;
import task.Todo;
import task.Deadline;
import task.Event;
import task.Task;
import ui.Ui;
import dukeexception.DukeException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    protected String filePath = "C:/repos/duke_new2/";

    /**Hi.*/
    public Storage(String filePath) {
        this.filePath += filePath;
    }

    /**Hi.*/
    public ArrayList<Task> read() throws IOException {
        ArrayList<Task> items = new ArrayList<>();
        Ui ui = new Ui();
        File file = new File(filePath);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        String taskDesc;
        String dateDesc;
        while ((st = br.readLine()) != null) {
            String[] commandList = st.split("\\|");
            try {
                //clear previous dates/desc
                taskDesc = "";
                dateDesc = "";
                for (int i = 0; i < commandList.length; i++) {
                    if (i == 2) {
                        taskDesc = commandList[i];
                    } else if (i == 3) {
                        dateDesc = commandList[i];
                    }
                }
                Boolean checked = false;
                if (commandList.length > 1) {
                    if (!(commandList[1].equals("1") || commandList[1].equals("0"))) {
                        throw new DukeException("Error reading 1 or 0, skipping to next line");
                    }
                    checked = commandList[1].equals("1");
                }
                Task t;
                if (commandList[0].equals("T")) {
                    if (taskDesc.trim().isEmpty()) {
                        throw new DukeException("Error reading description, skipping to next line");
                    } else {
                        t = new Todo(taskDesc);
                        t.setStatusIcon(checked);
                        items.add(t);
                    }
                } else if (commandList[0].equals("D")) {

                    if (taskDesc.trim().isEmpty() || dateDesc.trim().isEmpty()) {
                        throw new DukeException("Error reading description or date/time, skipping to next line");
                    } else {
                        t = new Deadline(taskDesc, dateDesc);
                        t.setStatusIcon(checked);
                        items.add(t);
                    }
                } else if (commandList[0].equals("E")) {
                    if (taskDesc.isEmpty() || dateDesc.isEmpty()) {
                        throw new DukeException("Error reading description or date/time, skipping to next line");
                    } else {
                        t = new Event(taskDesc, dateDesc);
                        t.setStatusIcon(checked);
                        items.add(t);
                    }
                } else if (!commandList[0].isEmpty()) {
                    throw new DukeException("Error reading whether if its T, D, or E, skipping to next line");
                }
            } catch (Exception e) {
                ui.showErrorMsg("     Error when reading current line, please fix the text file:");
                e.printStackTrace();
                ui.showErrorMsg("     Duke will continue reading the rest of file");
            }
        }
        br.close();
        return items;
    }

    /**Hi.*/
    public void write(TaskList items) throws IOException {
        String fileContent = "";
        for (int i = 0; i < items.size(); i++) {
            fileContent += items.get(i).toFile() + "\n";
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(fileContent);
        writer.close();
    }
}
