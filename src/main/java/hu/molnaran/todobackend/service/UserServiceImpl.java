package hu.molnaran.todobackend.service;

import hu.molnaran.todobackend.exception.UploadedFileNotFoundException;
import hu.molnaran.todobackend.exception.TypeNotAllowedException;
import hu.molnaran.todobackend.exception.EmailAlreadyExistException;
import hu.molnaran.todobackend.model.User;
import hu.molnaran.todobackend.repository.UserRepository;
import hu.molnaran.todobackend.util.FileUtil;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Value("${file.upload-dir}")
    private String uploadFolderPath;

    @Autowired
    private MessageSource messageSource;

    private File uploadDirectory;

    @PostConstruct
    public void init(){
        uploadDirectory = new File(uploadFolderPath);
        if (!uploadDirectory.exists()){
            uploadDirectory.mkdir();
        }
    }

    @Autowired
    private FileUtil fileUtil;

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
        return userRepository.save(user);
    }

    @Override
    public byte[] getAvatar(String path) {
        return fileUtil.getSingleFile(path, uploadDirectory);
    }

    @Override
    public User removeUser(long id) {
        User user =userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("The given user does not exist!"));
        fileUtil.deleteFilesFromDirectory(user.getAvatarPath(), uploadDirectory);
        userRepository.deleteById(id);
        return user;
    }

    @Override
    public User updateUser(long id, User user) {
        User updateToBeUpdated=userRepository.findById(id)
                .orElseThrow(EntityExistsException::new);
        if (user.getEmail()!=null){
            userRepository.findByEmail(user.getEmail()).ifPresent(user1 -> {throw new EmailAlreadyExistException();});
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
        String fileName = user.getId()+ "_avatar";
        updateAvatarFiles(fileName, file);
        user.setAvatarPath(fileName);
        userRepository.save(user);
        return user;
    }


    private void updateAvatarFiles(String filename, MultipartFile file){
        if (file.isEmpty()){
            throw new UploadedFileNotFoundException();
        }
        if (!isAllowedImageType(file)){
            throw new TypeNotAllowedException();
        }
        fileUtil.deleteFilesFromDirectory(filename, uploadDirectory);
        fileUtil.writeImage(filename, file, uploadDirectory);
    }

    private boolean isAllowedImageType(MultipartFile file) {
        try{
            Tika tika = new Tika();
            String detectedType = tika.detect(file.getBytes());
            return (detectedType.equals(MediaType.IMAGE_JPEG_VALUE) || detectedType.equals(MediaType.IMAGE_PNG_VALUE ));
        }catch (IOException io){
            io.printStackTrace();
            throw new TypeNotAllowedException();
        }
    }

}
