package hu.molnaran.todobackend.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Pattern(regexp = "[\\p{L} ]*", message = "Name has invalid characters!")
    @NotNull(message = "Name is mandatory!")
    @NotBlank(message = "Name is mandatory!")
    private String name;
    @Email
    @NotNull
    private String email;
    private String avatarPath;
    @Length(min = 6)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<Todo> todoList= new ArrayList<>();

    public void addTodo(Todo todo){
        this.todoList.add(todo);
    }

    public void removeTodo(Todo todo){
        this.todoList.remove(todo);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public List<Todo> getTodoList() {
        return todoList;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        for (Todo t: this.todoList) {
            sb.append(t).append("\n");
        }
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", avatarPath='" + avatarPath + '\'' +
                ", password='" + password + '\'' +
                ", todoList=" + sb.toString() +
                '}';
    }
}
