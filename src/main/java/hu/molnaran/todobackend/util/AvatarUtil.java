package hu.molnaran.todobackend.util;


import hu.molnaran.todobackend.exception.TypeNotAllowedException;
import hu.molnaran.todobackend.exception.UploadedFileNotFoundException;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Component
public class AvatarUtil {


    @Value("${file.upload-dir}")
    private String uploadFolderPath;

    @Value("${placeholder.avatarname}")
    private String placeHolderAvatarFile;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private FileUtil fileUtil;

    private File uploadDirectory;


    @PostConstruct
    public void init(){
        uploadDirectory = new File(uploadFolderPath);
        if (!uploadDirectory.exists()){
            uploadDirectory.mkdir();
        }
        try{
            fileUtil.writeResourceFile("images", placeHolderAvatarFile, uploadFolderPath);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

    }


    public byte[] getAvatar(String avatarName){
        try{
            if (avatarName.equals(placeHolderAvatarFile)){
                return fileUtil.getSingleFile(placeHolderAvatarFile, uploadDirectory);
            }
            return fileUtil.getSingleFile(avatarName, uploadDirectory);
        }catch (FileNotFoundException fne){
            throw new EntityNotFoundException("Avatar not found!");
        }catch (IOException fne){
            throw new EntityNotFoundException("Avatar not found!");
        }
    }

    public void removeAvatar(String avatarName){
        if (!avatarName.equals(placeHolderAvatarFile)){
            fileUtil.deleteFilesFromDirectory(avatarName, uploadDirectory);
        }
    }

    public String addAvatar(long id, MultipartFile file){
        String fileName = id +"_avatar";
        updateAvatarFiles(fileName, file);
        return fileName;
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

    public String getAvatarPlaceHolderName(){
        return  this.placeHolderAvatarFile;
    }


}
