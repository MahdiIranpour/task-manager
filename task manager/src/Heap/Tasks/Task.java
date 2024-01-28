package Heap.Tasks;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Objects;

public class Task implements Comparable, Serializable {

    private final String name;
    private final String description;
    private final LocalDateTime deadline;
    private String status;      // can be "to do" or "done"
    private final LinkedList<Subtask> subtasks;

    /** require name of task, description, deadline and status*/
    public Task(String name, String description, LocalDateTime deadline, String status) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.status = status;

        subtasks = new LinkedList<>();
    }

    /**adds a subtask for this task to the list of subtasks*/
    public void addSubTask(Subtask subTask) {
        subtasks.add(subTask);
        subtasks.sort(Subtask::compareTo);
    }

    /** get name of task*/
    public String getName() {
        return name;
    }

    /** get description of task*/
    public String getDescription() {
        return description;
    }

    /** get deadline of task*/
    public LocalDateTime getDeadline() {
        return deadline;
    }

    /** get status of task*/
    public String getStatus() {
        return status;
    }

    /** sets status of task*/
    public void setStatus(String status) {
        this.status = status;
    }

    /**finds subtask and returns.
     * returns null if subtask
     * doesnt exist*/
    public Subtask findSubtask(String subtaskName){
        for (Subtask subtask : subtasks){
            if (Objects.equals(subtask.getName(), subtaskName)) return subtask;
        }
        return null;
    }

    /** compares a task with this task
     * by their deadlines and returns -1 for lower,
     * 0 for equal, 1 for greater
     */
    @Override
    public int compareTo(Object o) {
        Task that = (Task) o;

        return this.deadline.compareTo(that.deadline);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline.toString() +
                ", status=" + status +
                ", subTasks=" + subtasks +
                '}';
    }

    /**set a subTask done*/
    public void setDoneSubtask(Subtask subtask) {
        if ((findSubtask(subtask.getName() )) != null) {
            subtask.setStatus("done");
        }
    }

    public Subtask highestSubtask() {

        for (Subtask subtask: subtasks){
            if (Objects.equals(subtask.getStatus(), "to do"))
                return subtask;
        }
        return null;
    }
}
