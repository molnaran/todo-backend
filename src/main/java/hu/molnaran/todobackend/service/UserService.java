package hu.molnaran.todobackend.service;

import hu.molnaran.todobackend.model.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface UserService {
    User findUserById(long id);
    User findUserByEmail(String email);
    List<User> findUsers();
    User createUser(@Valid User user);
    byte[] getAvatar(String path);
    User removeUser(long id);
    User updateUser(long id, User user);
    User addAvatar(long id, MultipartFile file);
}
