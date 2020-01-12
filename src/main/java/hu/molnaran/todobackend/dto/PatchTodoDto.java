package hu.molnaran.todobackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Future;
import java.util.Date;

public class PatchTodoDto {

    private Date dueDate;

    @Length(min=2, max = 50)
    private String title;

    private String description;

    private Boolean done;

    public Date getDueDate() {
        return dueDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getDone() {
        return done;
    }
}
