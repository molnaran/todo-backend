package hu.molnaran.todobackend.dto;

import java.util.Date;

public class CreateTodoDto {

    //@JsonDeserialize(using = CustomDateDeserializer.class)
    private Date dueDate;

    private String title;

    private String description;

    public Date getDueDate() {
        return dueDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}
