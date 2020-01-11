package hu.molnaran.todobackend.dto;

import hu.molnaran.todobackend.validation.FieldMatch;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@FieldMatch.List({
        @FieldMatch(fieldOne = "password", fieldTwo = "confirmPassword", message = "The password fields must match")
})
public class CreateUserDto {
    @Pattern(regexp = "[\\p{L} ]*", message = "Name has invalid characters!")
    @NotNull(message = "Name is mandatory!")
    @NotBlank(message = "Name is mandatory!")
    private String name;
    @Email
    @NotNull(message = "Email is mandatory!")
    @NotBlank(message = "Email is mandatory!")
    private String email;
    @Length(min = 6)
    private String password;
    private String confirmPassword;

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
