package eci.cosw.data.model;

import java.util.Objects;

public class Todo {

    private String description;
    private Integer priority;
    private String dueDate;
    private String responsible;
    private String status;

    public Todo() {
    }

    public Todo(String description, Integer priority, String dueDate, String responsible, String status) {
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.responsible = responsible;
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", responsible='" + responsible + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return Objects.equals(description, todo.description) &&
                Objects.equals(priority, todo.priority) &&
                Objects.equals(dueDate, todo.dueDate) &&
                Objects.equals(responsible, todo.responsible) &&
                Objects.equals(status, todo.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, priority, dueDate, responsible, status);
    }
}
