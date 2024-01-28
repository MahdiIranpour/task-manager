package Heap.Tasks;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Subtask implements Comparable, Serializable {

    private final String name;

    @Override
    public String toString() {
        return "Subtask{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline.toString() +
                ", status=" + status +
                '}';
    }

    private final String description;
    private final LocalDateTime deadline;
    private String status;      // can be "to do" or "done"
    private final Task task;

    /** require name of task, description, deadline and status*/
    public Subtask(String name, String description, LocalDateTime deadline, String status, Task task) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
        this.task = task;
    }

    /**gets name of subtask*/
    public String getName() {
        return name;
    }

    /**gets description of subtask*/
    public String getDescription() {
        return description;
    }

    /**gets deadline date of subtask*/
    public LocalDateTime getDeadline() {
        return deadline;
    }

    /**gets status of subtask*/
    public String getStatus() {
        return status;
    }

    /**sets status of subtask*/
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int compareTo(Object o) {
        Subtask that = (Subtask) o;

        return this.deadline.compareTo(that.deadline);
    }

    public Task getTask() {
        return task;
    }
}
