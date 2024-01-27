package Heap.Tasks;

import java.util.Date;

public class Task implements Comparable {

    private final String name;
    private final String description;
    private final Date deadline;

    private Status status;

    public Task(String name, String description, Date deadline, Status status) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int compareTo(Object o) {
        Task that = (Task) o;

        return this.deadline.compareTo(that.deadline);
    }
}
