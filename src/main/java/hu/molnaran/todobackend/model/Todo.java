package hu.molnaran.todobackend.model;


import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;

    @ManyToOne
    private User user;

    @NotNull
    @NotBlank
    @Length(min=2, max = 50)
    private String title;

    @Length(min=2, max=500)
    private String description;

    private Boolean done=false;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        String ownerId="nincs owner";
        if (this.user!=null){
            ownerId=this.user.getId()+"";
        }
        return "Todo{" +
                "id=" + id +
                ", dueDate=" + dueDate +
                ", owner=" + ownerId +
                ", title='" + title + '\'' +
                ", done='" + done + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Boolean isDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
