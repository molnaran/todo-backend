package hu.molnaran.todobackend.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class CreateTodoDto {

    @NotNull
    @Future
    private Date dueDate;

    @NotNull
    @NotBlank
    @Length(min=2, max = 50)
    private String title;

    @Length(min=2, max=500)
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
