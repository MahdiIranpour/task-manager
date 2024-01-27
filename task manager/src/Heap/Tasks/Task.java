package Heap.Tasks;

import java.util.Date;

public class Task implements Comparable {

    private final String name;
    private final String description;
    private final Date deadline;
    private Status status;      // can be "to do" or "done"

    /** require name of task, description, deadline and status*/
    public Task(String name, String description, Date deadline, Status status) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
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
    public Date getDeadline() {
        return deadline;
    }

    /** get status of task*/
    public Status getStatus() {
        return status;
    }

    /** sets status of task*/
    public void setStatus(Status status) {
        this.status = status;
    }

    /** compares a task with this task
     * by their deadlines and returns -1 for lower
     * , 0 for equal, 1 for greater
     */
    @Override
    public int compareTo(Object o) {
        Task that = (Task) o;

        return this.deadline.compareTo(that.deadline);
    }
}
