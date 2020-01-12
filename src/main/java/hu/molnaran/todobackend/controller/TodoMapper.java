package hu.molnaran.todobackend.controller;

import hu.molnaran.todobackend.dto.CreateTodoDto;
import hu.molnaran.todobackend.dto.CreateUserDto;
import hu.molnaran.todobackend.dto.PatchTodoDto;
import hu.molnaran.todobackend.dto.TodoDto;
import hu.molnaran.todobackend.model.Todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TodoMapper {

    public static List<TodoDto> mapTodosToTodoDtos(List<Todo> todoList){
        List<TodoDto> userDtoList= new ArrayList<>();
        for (Todo todo:todoList) {
            userDtoList.add(mapTodoToTodoDto(todo));
        }
        return userDtoList;
    }

    public static Todo mapTodoDtoToTodo(CreateTodoDto createTodoDto){
        Todo todo= new Todo();
        todo.setDueDate(createTodoDto.getDueDate());
        todo.setTitle(createTodoDto.getTitle());
        todo.setDescription(createTodoDto.getDescription());
        System.out.println(createTodoDto);
        return todo;
    }

    public static Todo mapPatchUserDtoToUser(PatchTodoDto patchTodoDto){
        Todo todo= new Todo();
        todo.setDueDate(patchTodoDto.getDueDate());
        todo.setTitle(patchTodoDto.getTitle());
        todo.setDescription(patchTodoDto.getDescription());
        todo.setDone(patchTodoDto.getDone());
        return todo;
    }

    public static TodoDto mapTodoToTodoDto(Todo todo){
        TodoDto todoDto= new TodoDto();
        todoDto.setId(todo.getId());
        todoDto.setDescription(todo.getDescription());
        todoDto.setTitle(todo.getTitle());
        todoDto.setDone(todo.isDone());
        todoDto.setDueDate(todo.getDueDate());
        todoDto.setUserId(todo.getId());
        return todoDto;
    }
}
