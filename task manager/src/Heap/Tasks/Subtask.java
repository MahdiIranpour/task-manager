package Heap.Tasks;

import java.util.Date;

public class Subtask implements Comparable {

    private final String name;
    private final String description;
    private final Date deadline;
    private Status status;      // can be "to do" or "done"

    /** require name of task, description, deadline and status*/
    public Subtask(String name, String description, Date deadline, Status status) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
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
    public Date getDeadline() {
        return deadline;
    }

    /**gets status of subtask*/
    public Status getStatus() {
        return status;
    }

    /**sets status of subtask*/
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int compareTo(Object o) {
        Subtask that = (Subtask) o;

        return this.deadline.compareTo(that.deadline);
    }
}
