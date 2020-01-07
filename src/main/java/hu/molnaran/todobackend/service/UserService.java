package hu.molnaran.todobackend.service;

import hu.molnaran.todobackend.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User findUserById(long id);
    User findUserByEmail(String email);
    List<User> findUsers();
    User createUser(User user);

    byte[] getAvatar(String path);
    User removeUser(long id);
    User updateUser(long id, User user);
    User addAvatar(long id, MultipartFile file);
}
