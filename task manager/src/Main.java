import Heap.MinHeap;
import Heap.Tasks.Status;
import Heap.Tasks.Subtask;
import Heap.Tasks.Task;

import java.io.*;
import java.sql.Date;
import java.util.Objects;


public class Main {

    static MinHeap tasks = new MinHeap();

    public static void main(String[] args) {

        try (FileInputStream fis = new FileInputStream(""); InputStreamReader isr = new InputStreamReader(fis); BufferedReader reader = new BufferedReader(isr))
        {

            String line;
            while ((line = reader.readLine()) != null){

                String [] words = line.split(" ");

                switch (words[0]){
                    case "add" ->{
                        adding(words[1], line);
                    }
                    case "done" ->{
                        setSubtaskStatus(line);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void setSubtaskStatus(String line) {

        String woDone = line.substring(5);
        String[] words = woDone.split(",");

        Task task = findTask(words[0]);
        if (task != null) {
            Subtask subtask = task.findSubtask(words[1]);

            if (subtask != null)
                task.setDoneSubtask(subtask);
        }
    }

    private static void adding(String taskType, String line) {

        switch (taskType){

            case "task" -> {
                addTask(line);
            }
            case "subTask" -> {
                addSubTask(line);
            }
        }
    }

    private static void addSubTask(String line) {
        String actualTask = line.substring(12);

        String [] words = actualTask.split(",");

        Task task = findTask(words[4]);

        if(!(task == null)) {

            Subtask subtask = new Subtask(words[0], words[1], Date.valueOf(words[2]), Status.valueOf(words[3]), task);

            task.addSubTask(subtask);
        }
    }

    private static Task findTask(String TaskName) {

        for (Task t : tasks.getHeap()){
            if (Objects.equals(t.getName(), TaskName)){
                return t;
            }
        }
        return null;
    }

    private static void addTask(String line) {

        String actualTask = line.substring(9);

        String [] words = actualTask.split(",");

        Task task = new Task(words[0], words[1], Date.valueOf( words[2]), Status.valueOf(words[3]));

        tasks.insert(task);
    }
}