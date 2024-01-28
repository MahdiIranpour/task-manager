import Heap.MinHeap;
import Heap.Tasks.Status;
import Heap.Tasks.Subtask;
import Heap.Tasks.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    static MinHeap tasks = new MinHeap();

    public static void main(String[] args) {

//        try (FileInputStream fis = new FileInputStream(""); InputStreamReader isr = new InputStreamReader(fis); BufferedReader reader = new BufferedReader(isr))
//        {
//
//            String line;
//            while ((line = reader.readLine()) != null){
//
//                String [] words = line.split(" ");
//
//                switch (words[0]){
//                    case "add" ->{
//                        adding(words[1], line);
//                    }
//                    case "subDone" ->{
//                        setSubtaskStatus(line);
//                    }
//                    case "taskDone"-> {
//                        setDoneTask(words[1]);
//                    }
//                }
//            }
//
//        } catch (IOException e) {
//            print(e.getMessage());
//        }

        Scanner sc = new Scanner(System.in);

        String line;
        while (sc.hasNext()) {

            line = sc.nextLine();
            String[] words = line.split(" ");

            switch (words[0]) {
                case "add" -> adding(words[1], line);
                case "subDone" -> setSubtaskStatus(line);
                case "taskDone" -> setDoneTask(words[1]);
                case "show" -> printHeap();
                case "first" -> print(tasks.getMin().toString());
            }
        }
    }

    private static void setDoneTask(String taskName) {

        Task t;
        if (Objects.equals((t = tasks.getMin()).getName(), taskName)) {

            if (t.highestSubtask() == null) {

                tasks.extractMin();
                print("task " + t.getName() + " Done!");
            } else
                print("still there is a subtask to do for" + taskName);
        } else
            print("this task is not in highest priority");
    }

    private static void setSubtaskStatus(String line) {

        String woDone = line.substring(8);
        String[] words = woDone.split(",\\s");

        Task task = findTask(words[1]);
        if (task != null) {

            if (tasks.getMin() == task ){
                Subtask subtask = task.findSubtask(words[0]);

                if (subtask != null) {
                    if (task.highestSubtask() == subtask) {

                        task.setDoneSubtask(subtask);
                        print(subtask.getName() + " done!");

                        setDoneTask(task.getName());
                    } else
                        print("subtask is not in highest priority");
                } else
                    print("subtask is not found");
            } else
                print("task is not in highest priority");
        } else
            print("task is not found");
    }

    private static void adding(String taskType, String line) {

        switch (taskType) {

            case "task" -> addTask(line);
            case "subtask" -> addSubTask(line);
        }
    }

    private static void addSubTask(String line) {
        String actualTask = line.substring(12);

        String[] words = actualTask.split(", ");

        Task task = findTask(words[4]);

        if (!(task == null)) {


            // Define the desired date format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

            // Parse the string into a LocalDate object
            LocalDateTime parsedDate = LocalDateTime.parse(words[2], formatter);


            Subtask subtask = new Subtask(words[0], words[1], parsedDate, words[3], task);

            task.addSubTask(subtask);
        }
    }

    private static Task findTask(String TaskName) {

        for (Task t : tasks.getHeap()) {
            if (Objects.equals(t.getName(), TaskName)) {
                return t;
            }
        }
        return null;
    }

    private static void addTask(String line) {

        String actualTask = line.substring(9);

        String[] words = actualTask.split(", ");

        // Define the desired date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

        // Parse the string into a LocalDate object
        LocalDateTime parsedDate = LocalDateTime.parse(words[2], formatter);

        Task task = new Task(words[0], words[1], parsedDate, words[3]);

        tasks.insert(task);
    }

    static void printHeap() {

        for (Task t : tasks.getHeap()) {
            System.out.println(t.toString());
        }
    }

    static void print(String line) {
        System.out.println(line);
    }
}