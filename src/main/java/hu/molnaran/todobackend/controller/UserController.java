package hu.molnaran.todobackend.controller;


import hu.molnaran.todobackend.dto.CreateUserDto;
import hu.molnaran.todobackend.dto.PatchUserDto;
import hu.molnaran.todobackend.dto.UserDto;
import hu.molnaran.todobackend.model.User;
import hu.molnaran.todobackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@Validated
@RequestMapping("/user/")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDto> getUsers(){
        List<User> userList=userService.findUsers();
        return UserMapper.mapUsersToUserDtos(userList);
    }

    @GetMapping(path = "/{userId}")
    public UserDto getUser(@PathVariable(value = "userId") long userId){
        User user=userService.findUserById(userId);
        return UserMapper.mapUserToUserDto(user);
    }

    @PostMapping
    public UserDto addUser(@RequestBody @Valid CreateUserDto userDto){
        User createdUser=userService.createUser(UserMapper.mapCreateUserDtoToUser(userDto));
        return UserMapper.mapUserToUserDto(createdUser);
    }

    @PatchMapping(path = "/{userId}")
    public UserDto updateUser(@PathVariable(value = "userId") long userId, @Valid  @RequestBody PatchUserDto userDto){
        User updatedUser = userService.updateUser(userId, UserMapper.mapPatchUserDtoToUser(userDto));
        return UserMapper.mapUserToUserDto(updatedUser);
    }

    @DeleteMapping(path="/{userId}")
    public UserDto removeUser(@PathVariable(value = "userId") long userId){
        User removedUser = userService.removeUser(userId);
        return UserMapper.mapUserToUserDto(removedUser);
    }

    @PostMapping(path = "/{userId}/avatar/")
    public User uploadToLocalFileSystem(@PathVariable(value = "userId") long userId, @RequestParam("avatar") MultipartFile avatarFile) {
        return userService.addAvatar(userId, avatarFile);
    }

    @GetMapping(path = "/avatar/{avatarPath}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] getImageWithMediaType(@PathVariable("avatarPath") String avatarPath) throws IOException {
        return userService.getAvatar(avatarPath);
    }

}
