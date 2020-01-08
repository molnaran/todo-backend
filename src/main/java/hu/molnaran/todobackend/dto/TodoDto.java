package hu.molnaran.todobackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TodoDto {

    private long id;

    private Date dueDate;

    private long userId;

    private String title;

    private String description;

    private Boolean done;

    public void setId(long id) {
        this.id = id;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Boolean isDone() {
        return done;
    }

    @Override
    public String toString() {
        return "TodoDto{" +
                "id=" + id +
                ", dueDate=" + dueDate +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", done=" + done +
                '}';
    }
}
