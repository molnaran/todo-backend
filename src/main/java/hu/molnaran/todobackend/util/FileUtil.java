package hu.molnaran.todobackend.util;

import hu.molnaran.todobackend.exception.AvatarNotFoundException;
import hu.molnaran.todobackend.exception.AvatarUploadException;
import hu.molnaran.todobackend.exception.NotAcceptableMimeTypeException;
import hu.molnaran.todobackend.exception.TypeNotAllowedException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUtil {

    public void writeImage(String fileName, MultipartFile file, File directory){
        try {
            Path path = Paths.get(directory+"/" + fileName+"."+ FilenameUtils.getExtension(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AvatarUploadException();
        }
    }

    public byte[] getSingleFile(String startingWith, File directory){
        try{
            File[] filteredFiles = directory.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name)
                {
                    return name.startsWith(startingWith + ".");
                }
            });
            if (filteredFiles.length>0){
                byte[] imageBytes = Files.readAllBytes(filteredFiles[0].toPath());
                return imageBytes;
            }else{
                throw new AvatarNotFoundException();
            }
        }catch (IOException ioex){
            ioex.printStackTrace();
            throw new AvatarNotFoundException();
        }
    }


    public void deleteFilesFromDirectory(String startingWith, File directory){
        try{
            File[] filteredFiles = directory.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name)
                {
                    return name.startsWith(startingWith + ".");
                }
            });
            if (filteredFiles.length>0){
                for (int i = 0; i < filteredFiles.length; i++) {
                    File fileToDelete = FileUtils.getFile(directory,filteredFiles[i].getName());
                    FileUtils.deleteQuietly(fileToDelete);
                }
            }

        }catch (NullPointerException npe){
            System.out.println("Exc");
        }
    }

}
