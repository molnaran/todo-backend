package hu.molnaran.todobackend.service;

import hu.molnaran.todobackend.exception.EmailAlreadyExistException;
import hu.molnaran.todobackend.model.User;
import hu.molnaran.todobackend.repository.UserRepository;
import hu.molnaran.todobackend.util.AvatarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private AvatarUtil avatarUtil;



    @Override
    public User findUserById(long id) {
        return userRepository.findById(id).orElseThrow(() ->  new EntityNotFoundException("User not found!"));
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    @Override
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        userRepository.findByEmail(user.getEmail()).ifPresent(user1 -> {throw new EmailAlreadyExistException();});
        user.setAvatarPath(avatarUtil.getAvatarPlaceHolderName());
        return userRepository.save(user);
    }

    @Override
    public byte[] getAvatar(String avatar) {
        return avatarUtil.getAvatar(avatar);
    }

    @Override
    public User removeUser(long id) {
        User user =userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("The given user does not exist!"));
        avatarUtil.removeAvatar(user.getAvatarPath());
        userRepository.deleteById(id);
        return user;
    }

    @Override
    public User updateUser(long id, User user) {
        User updateToBeUpdated=userRepository.findById(id)
                .orElseThrow(EntityExistsException::new);

        if (user.getEmail()!=null){
            userRepository.findByEmail(user.getEmail()).ifPresent(user1 -> {if (user1.getId() != id){
                throw new EmailAlreadyExistException();
            }
            });
            updateToBeUpdated.setEmail(user.getEmail());
        }
        if (user.getPassword()!=null){
            updateToBeUpdated.setPassword(user.getPassword());
        }
        if (user.getName()!=null){
            updateToBeUpdated.setName(user.getName());
        }
        return userRepository.save(updateToBeUpdated);
    }

    @Override
    public User addAvatar(long id, MultipartFile file) {
        User user = userRepository.findById(id).orElseThrow(EntityExistsException::new);
        String avatarName=avatarUtil.addAvatar(id, file);
        user.setAvatarPath(avatarName);
        userRepository.save(user);
        return user;
    }



}
