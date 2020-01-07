package hu.molnaran.todobackend.controller;

import hu.molnaran.todobackend.dto.UserDto;
import hu.molnaran.todobackend.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static List<UserDto> mapUsersToUserDtos(List<User> userList){
        List<UserDto> userDtoList= new ArrayList<>();
        for (User user:userList) {
            userDtoList.add(mapUserToUserDto(user));
        }
        return userDtoList;
    }

    public static List<User> mapUserDtosToUsers(List<UserDto> userDtoList){
        List<User> userList= new ArrayList<>();
        for (UserDto userDto:userDtoList) {
            userList.add(mapUserDtoToUser(userDto));
        }
        return userList;
    }

    public static UserDto mapUserToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setAvatarPath(user.getAvatarPath());
        return userDto;
    }

    public static User mapUserDtoToUser(UserDto userDto){
        User user= new User();
        user.setId(userDto.getId());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        return user;
    }


}
